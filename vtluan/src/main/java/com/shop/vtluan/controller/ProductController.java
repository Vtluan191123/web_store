package com.shop.vtluan.controller;

import java.io.IOException;
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
import com.shop.vtluan.service.CartService;
import com.shop.vtluan.service.Cart_detailService;
import com.shop.vtluan.service.ProductService;
import com.shop.vtluan.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {
    private final ProductService productService;
    private final Cart_detailService cart_detailService;
    private final UserService userService;
    private final CartService cartService;

    public ProductController(ProductService productService, Cart_detailService cart_detailService,
            UserService userService, CartService cartService) {
        this.productService = productService;
        this.cart_detailService = cart_detailService;
        this.userService = userService;
        this.cartService = cartService;
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
        Page<Products> productsPage = this.productService.getProducts(pageable);
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
    public String getAddToCart(HttpSession session, Optional<Long> productId) {
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

        double itemPrice = Double.parseDouble(products.get().getPrice());
        // cart not exist
        if (cart == null && products.isPresent()) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            newCart.setProduct_total(0);
            newCart.setTotal_price(0);
            cart = this.cartService.saveCart(newCart);
        }

        // Save total price
        cart.setTotal_price(cart.getTotal_price() + itemPrice);
        this.cartService.saveCart(cart);

        Optional<Cart_detail> cart_detailOld = this.cart_detailService.checkExistProductAndCart(products.get(),
                cart);
        if (cart_detailOld.isEmpty()) {
            Cart_detail cart_detail = new Cart_detail();
            cart_detail.setCart(cart);
            cart_detail.setProducts(products.get());
            cart_detail.setQuantity(1);
            this.cart_detailService.saveCart_detail(cart_detail);

            // set product total of cart
            int sum = cart.getProduct_total() + 1;
            cart.setProduct_total(sum);
            session.setAttribute("total", sum);
            this.cartService.saveCart(cart);
        } else {
            int sum = cart_detailOld.get().getQuantity() + 1;
            cart_detailOld.get().setQuantity(sum);
            this.cart_detailService.saveCart_detail(cart_detailOld.get());
        }

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCart(Model model, HttpSession session) {
        String email = (String) session.getAttribute("emailSession");
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart();
        List<Cart_detail> listCart_details = this.cart_detailService.getListCart_detail(cart);
        model.addAttribute("listCart_details", listCart_details);
        return "user/cart";
    }

    @PostMapping("/delete_item_cart/{id}")
    public String postDeleteItemCart(@PathVariable("id") long id, HttpSession session) {
        String email = (String) session.getAttribute("emailSession");
        User user = this.userService.getUserByEmail(email);
        Cart cart = user.getCart();
        Optional<Cart_detail> cart_detail = this.cart_detailService.findCart_DetailById(id);
        if (cart_detail.isPresent()) {
            this.cart_detailService.deleteItemCart(cart_detail.get());
        }
        int sum = cart.getProduct_total() - 1;
        cart.setProduct_total(sum);
        this.cartService.saveCart(cart);
        session.setAttribute("total", sum);
        return "redirect:/cart";
    }

}
