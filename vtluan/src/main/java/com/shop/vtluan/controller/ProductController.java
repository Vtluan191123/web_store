package com.shop.vtluan.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.shop.vtluan.model.Cart;
import com.shop.vtluan.model.Cart_detail;
import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.User;
import com.shop.vtluan.model.DTO.ReceiverDto;
import com.shop.vtluan.service.CartService;
import com.shop.vtluan.service.Cart_detailService;
import com.shop.vtluan.service.ProductService;
import com.shop.vtluan.service.UserService;
import com.shop.vtluan.service.VNPayService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    private final ProductService productService;
    private final Cart_detailService cart_detailService;
    private final UserService userService;
    private final CartService cartService;
    private final VNPayService vnPayService;

    public ProductController(ProductService productService, Cart_detailService cart_detailService,
            UserService userService, CartService cartService, VNPayService vnPayService) {
        this.productService = productService;
        this.cart_detailService = cart_detailService;
        this.userService = userService;
        this.cartService = cartService;
        this.vnPayService = vnPayService;
    }

    @GetMapping("admin/products")
    public String getTableProducts(Model model, @RequestParam("search") Optional<String> search,
            @RequestParam("page") Optional<String> pageOptional) {
        int page = 1;

        try {
            if (pageOptional.isPresent()) {
                int check = Integer.parseInt(pageOptional.get());
                if (check > 0) {
                    page = check;
                } else {
                    page = 1;
                }
            }
        } catch (Exception e) {
            System.out.println(">> run here");
        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<Products> productsPage = this.productService.getAllProducts(pageable);
        List<Products> products = productsPage.getContent();
        model.addAttribute("products", products);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", productsPage.getTotalPages());
        if (search.isPresent()) {
            List<Products> results = this.productService.searchByNameproduct(search.get());
            model.addAttribute("search", search);
            model.addAttribute("results", results);
        }
        return "admin/manage_products/table_product";
    }

    @GetMapping("admin/product/create")
    public String getCreateProduct(Model model) {
        Products product = new Products();
        model.addAttribute("products", product);
        return "admin/manage_products/create_product";
    }

    @PostMapping("/admin/product/created")
    public String postCreateProduct(@RequestParam("file") MultipartFile file,
            @ModelAttribute @Valid Products product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(">>>" + fieldError.getField() + ">>>" +
                        fieldError.getDefaultMessage());
            }
            return "admin/manage_products/create_product";
        }
        // upload file
        String image = this.productService.upLoadFile(file);
        product.setImage(image != null ? image : "");
        this.productService.saveProducts(product);

        return "redirect:/admin/products ";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(@PathVariable("id") long id, Model model) {
        Optional<Products> proOptional = this.productService.findProduct(id);
        if (proOptional.isPresent()) {
            model.addAttribute("products", proOptional.get());
        }
        return "admin/manage_products/delete_product";
    }

    @PostMapping("/admin/product/deleted")
    public String postDeleteProduct(@ModelAttribute Products product) {
        this.productService.removeProduct(product.getId());

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String getEditProduct(@PathVariable("id") long id, Model model) {
        Optional<Products> proOptional = this.productService.findProduct(id);
        if (proOptional.isPresent()) {
            model.addAttribute("products", proOptional.get());
        }
        return "admin/manage_products/edit_product";
    }

    @PostMapping("/admin/product/edited")
    public String postEditProduct(@ModelAttribute Products products, @RequestParam("file") MultipartFile imageFile)
            throws IOException {
        Products currentProduct = new Products();
        if (products != null) {
            currentProduct.setId(products.getId());
            currentProduct.setDescription(products.getDescription());
            currentProduct.setPrice(products.getPrice());
            currentProduct.setDes_short(products.getDes_short());
            currentProduct.setName(products.getName());
            currentProduct.setBrand(products.getBrand());

            if (imageFile.isEmpty()) {
                currentProduct.setImage(products.getImage());
                System.out.println("run here");
            } else {
                this.productService.upLoadFile(imageFile);
                this.productService.deleteFile(products.getImage());
                currentProduct.setImage(imageFile.getOriginalFilename());
            }

            System.out.println("run here >>" + currentProduct);
            this.productService.saveProducts(currentProduct);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/view/{id}")
    public String getViewUser(@PathVariable("id") long id, Model model) {
        Optional<Products> proOptional = this.productService.findProduct(id);
        if (proOptional.isPresent()) {
            model.addAttribute("product", proOptional.get());
        }
        return "admin/manage_products/view_product";
    }

    @GetMapping("/add_to_cart")
    public String getAddToCart(HttpSession session,
            @RequestParam Optional<Long> productId,
            @RequestParam Optional<Integer> quantityItem) {
        // get email
        String email = (String) session.getAttribute("emailSession");

        // get product
        Optional<Products> products = Optional.empty();
        if (productId.isPresent()) {
            products = this.productService.findProduct(productId.get());
        }
        // check user exist cart
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart();
        double itemPrice = 0;
        if (products.isPresent()) {
            itemPrice = products.get().getPrice();
        }

        // cart not exist
        if (cart == null) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProduct_total(0);
            newCart.setTotal_price(0);
            cart = this.cartService.saveCart(newCart);
        }

        // Save total price
        if (quantityItem.isPresent()) {
            cart.setTotal_price(cart.getTotal_price() + itemPrice * quantityItem.get());
            this.cartService.saveCart(cart);
        } else {
            cart.setTotal_price(cart.getTotal_price() + itemPrice);
            this.cartService.saveCart(cart);
        }

        Optional<Cart_detail> cart_detailOld = this.cart_detailService.checkExistProductAndCart(products.get(),
                cart);
        if (cart_detailOld.isEmpty()) {
            Cart_detail cart_detail = new Cart_detail();
            cart_detail.setCart(cart);
            cart_detail.setProducts(products.get());
            cart_detail.setQuantity(quantityItem.isPresent() ? quantityItem.get() : 1);
            this.cart_detailService.saveCart_detail(cart_detail);

            // set product total of cart
            int sum = cart.getProduct_total() + 1;
            cart.setProduct_total(sum);
            session.setAttribute("total", sum);
            this.cartService.saveCart(cart);
        } else {
            if (quantityItem.isPresent()) {
                int sum = cart_detailOld.get().getQuantity() + quantityItem.get();
                cart_detailOld.get().setQuantity(sum);
            } else {
                int sum = cart_detailOld.get().getQuantity() + 1;
                cart_detailOld.get().setQuantity(sum);
            }

            this.cart_detailService.saveCart_detail(cart_detailOld.get());
        }

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        String email = (String) session.getAttribute("emailSession");
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart() == null ? null : user.getCart();
        List<Cart_detail> listCart_details = this.cart_detailService.getListCart_detail(cart);
        double totalPrice = cart == null ? 0 : cart.getTotal_price();
        model.addAttribute("totalPrice", totalPrice);
        if (listCart_details == null) {
            model.addAttribute("listCart_details", new ArrayList<>());
        } else {
            model.addAttribute("listCart_details", listCart_details);
        }

        return "user/cart";
    }

    @PostMapping("/delete_item_cart/{id}")
    public String postDeleteItemCart(@PathVariable("id") long id, HttpSession session) {
        String email = (String) session.getAttribute("emailSession");
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart();
        Optional<Cart_detail> cart_detail = this.cart_detailService.findCart_DetailById(id);
        double totalProductsOfCart_detail = 0;
        if (cart_detail.isPresent()) {
            this.cart_detailService.deleteItemCart(cart_detail.get());
            Optional<Products> product = this.productService.getProductsById(cart_detail.get().getProducts().getId());
            if (product.isPresent()) {
                totalProductsOfCart_detail = cart_detail.get().getQuantity() *
                        product.get().getPrice();
            }
        }
        int sum = cart.getProduct_total() - 1;
        double currentPrice = cart.getTotal_price();
        cart.setProduct_total(sum);
        cart.setTotal_price(currentPrice - totalProductsOfCart_detail);
        this.cartService.saveCart(cart);
        session.setAttribute("total", sum);
        return "redirect:/cart";
    }

    @SuppressWarnings("null")
    @PostMapping("/checkout")
    public String postCheckOut(HttpSession session, Model model, @RequestParam("productId") List<Long> id,
            @RequestParam("productQuantity") List<Integer> quantityItem,
            @RequestParam("totalPrice") double totalPrice) {
        String email = (String) session.getAttribute("emailSession");
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart() == null ? null : user.getCart();
        // update cart_detail
        // get cart_detail by cart with product
        int index = 0;
        for (long itemId : id) {
            Optional<Products> products = this.productService.findProduct(itemId);
            if (products.isPresent()) {
                Optional<Cart_detail> updateCart_detail = this.cart_detailService
                        .checkExistProductAndCart(products.get(), cart);
                if (updateCart_detail.isPresent()) {
                    updateCart_detail.get().setQuantity(quantityItem.get(index));
                }
                this.cart_detailService.saveCart_detail(updateCart_detail.get());
            }
            index++;
        }
        cart.setTotal_price(totalPrice);
        this.cartService.saveCart(cart);

        List<Cart_detail> listCart_details = this.cart_detailService.getListCart_detail(cart);
        model.addAttribute("listCart_details", listCart_details);
        model.addAttribute("totalPrice", totalPrice);
        return "user/checkout";
    }

    @PostMapping("/order")
    public String submidOrder(@RequestParam("amount") long orderTotal,
            ReceiverDto receiverDto,
            HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String vnpayUrl = vnPayService.createOrder(orderTotal, receiverDto, baseUrl);
        return "redirect:" + vnpayUrl;
    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String nameInfo = request.getParameter("vnp_NameInfo");
        String phoneNumberInfo = request.getParameter("vnp_PhoneNumberInfo");
        String addressInfo = request.getParameter("vnp_AddressInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");
        String orderInfor = request.getParameter("vnp_OrderInfo");

        // Định dạng để phân tích chuỗi đầu vào
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Phân tích chuỗi thành LocalDateTime
        LocalDateTime dateTime = LocalDateTime.parse(paymentTime, inputFormatter);

        // Định dạng lại thành chuỗi thời gian dễ đọc
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Định dạng lại thời gian
        String formattedTime = dateTime.format(outputFormatter);

        long totalPriceFormat = Long.parseLong(totalPrice);
        totalPriceFormat = totalPriceFormat / 100;

        model.addAttribute("name", nameInfo);
        model.addAttribute("phoneNumber", phoneNumberInfo);
        model.addAttribute("address", addressInfo);
        model.addAttribute("totalPrice", totalPriceFormat);
        model.addAttribute("paymentTime", formattedTime);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("orderInfor", orderInfor);

        return paymentStatus == 1 ? "user/ordersuccess" : "user/orderfail";
    }

}
