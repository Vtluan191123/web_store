package com.shop.vtluan.controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.Products_;
import com.shop.vtluan.model.Token;
import com.shop.vtluan.model.User;
import com.shop.vtluan.model.DTO.ProductsCriteriaDto;
import com.shop.vtluan.model.DTO.UserDto;
import com.shop.vtluan.service.EmailService;
import com.shop.vtluan.service.ProductService;
import com.shop.vtluan.service.TokenService;
import com.shop.vtluan.service.UserService;

import jakarta.validation.Valid;

@Controller
public class HomePageController {
    private final EmailService emailService;
    private final UserService userService;
    private final TokenService tokenService;
    private final ProductService productService;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(UserService userService, ProductService productService, TokenService tokenService,
            EmailService emailService, PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;

    }

    @GetMapping("/")
    public String getHomePage(Model model, @RequestParam Optional<String> pageNum,
            @RequestParam("search_home") Optional<String> nameProduct) {

        int page = 1;
        try {
            if (pageNum.isPresent()) {
                int check = Integer.parseInt(pageNum.get());
                if (check > 0) {
                    page = check;
                } else {
                    page = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }

        Pageable pageable = PageRequest.of(page - 1, 8);
        if (nameProduct.isPresent()) {
            List<Products> searchs = this.productService.searchByNameproduct(nameProduct.get()) != null
                    ? this.productService.searchByNameproduct(nameProduct.get())
                    : new ArrayList<>();
            model.addAttribute("products", searchs);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPage", searchs.size() / 8 + 1);
        } else {
            Page<Products> pageProducts = this.productService.getAllProducts(pageable);
            List<Products> products = pageProducts.getContent();
            model.addAttribute("products", products);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPage", pageProducts.getTotalPages());
        }
        // search == empty
        return "user/homepage";
    }

    @GetMapping("/shop")
    public String getShop(Model model,
            ProductsCriteriaDto productsCriteriaDto) {
        int page = 1;
        try {
            if (productsCriteriaDto.getPage().isPresent()) {
                int check = Integer.parseInt(productsCriteriaDto.getPage().get());
                if (check > 0) {
                    page = check;
                } else {
                    page = 1;
                }
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e.getMessage());
        }

        Pageable pageable = null;

        if (productsCriteriaDto.getSort().get().equals("gia-tang-dan")) {
            pageable = PageRequest.of(page - 1, 60, Sort.by(Products_.PRICE).ascending());
        } else if (productsCriteriaDto.getSort().get().equals("gia-giam-dan")) {
            pageable = PageRequest.of(page - 1, 60, Sort.by(Products_.PRICE).descending());
        } else {
            pageable = PageRequest.of(page - 1, 60);
        }

        Page<Products> pageProducts = this.productService.getCriteriaWithSpec(pageable, productsCriteriaDto);
        // Page<Products> pageProducts =
        // this.productService.getProductsByNameLike(pageable, name);
        List<Products> products = pageProducts.getContent();
        model.addAttribute("products", products);
        return "user/shop";
    }

    @GetMapping("/product_detail/{id}")
    public String getProductDetail(@PathVariable long id, Model model) {
        Optional<Products> producOptional = this.productService.getProductsById(id);
        if (producOptional.isPresent()) {
            model.addAttribute("product", producOptional.get());
        }
        return "user/product_detail";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String postMethodName(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : fieldErrors) {
                System.out.println(">> " + fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
            System.out.println(">> run here");
            return "auth/register";
        }
        User user = this.userService.registerDTOtoUser(userDto);
        this.userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping("/access-deny")
    public String getAccessDeny() {
        return "error/accessdeny";
    }

    @GetMapping("/forgot_password")
    public String getForgotPassword() {
        return "auth/forgot";
    }

    @PostMapping("/send_email")
    public String postForgotPassword(@RequestParam("email") Optional<String> email, Model model) {
        if (email.isPresent()) {
            User user = this.userService.getUserByEmail(email.get());
            if (user == null) {
                model.addAttribute("error", "Email không tồn tại");
                return "auth/forgot";
            }
            // create token
            String token = UUID.randomUUID().toString();
            // Create expiry time
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 2);
            String expiryTime = format.format(calendar.getTime());
            System.out.println("run here>>" + expiryTime);

            // check user exist token
            if (user.getToken() != null) {
                // update token
                this.tokenService.updateToken(token, expiryTime, user.getToken());
            } else {
                // Save Obj Token
                this.tokenService.saveToken(token, expiryTime, user);
            }

            // Send mail
            String resetUrl = "http://localhost:8080/reset-password?token=" + token;
            emailService.sendResetPasswordEmail(user.getEmail(), resetUrl);

            model.addAttribute("message", "Kiểm tra email của bạn để đặt lại mật khẩu.");
        }
        return "auth/forgot";
    }

    @GetMapping("/reset-password")
    public String postResetPassword(@RequestParam("token") String token, Model model) throws ParseException {
        Token tokenFind = this.tokenService.findTokenByToken(token);

        if (tokenFind == null) {
            model.addAttribute("tokenNotFound", "Token Not Found");
            return "auth/token_expiry";
        }
        // check time expiry
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(tokenFind.getExpiryTime());
        boolean checkExpiryTime = date.before(new Date());
        System.out.println("time check " + date);
        System.out.println(new Date());
        System.out.println("run here >>" + checkExpiryTime);
        if (checkExpiryTime) {
            model.addAttribute("tokenExpired", "Token Expiry");
            this.tokenService.deleteToken(tokenFind);
            return "auth/token_expiry";
        }

        model.addAttribute("token", token);

        return "auth/changer_password";
    }

    @PostMapping("/changer_password")
    public String postChangrtPassword(@RequestParam("password") Optional<String> passwordOptional,
            @RequestParam("confirm") Optional<String> confirmOptional,
            @RequestParam("token") Optional<String> token,
            Model model) {
        // Find Token by token
        Token tokenFind = this.tokenService.findTokenByToken(token.get());
        if (passwordOptional.isEmpty() || confirmOptional.isEmpty()
                || !passwordOptional.equals(confirmOptional)) {
            model.addAttribute("message", "Thông tin không để trống ");
            return "auth/changer_password";
        }

        if (tokenFind != null) {
            User user = tokenFind.getUser();
            if (user != null) {
                user.setPassword(passwordEncoder.encode(passwordOptional.get()));
                this.userService.saveUser(user);
                // delete token
                this.tokenService.deleteToken(tokenFind);
            }

        }

        return "redirect:/login";
    }

}
