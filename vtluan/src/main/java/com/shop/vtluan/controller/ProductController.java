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

import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.User;
import com.shop.vtluan.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
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

    // @PostMapping("/admin/user/search")
    // public String search(@RequestParam(name = "query", required = false) String
    // query, Model model) {
    // if (query != null && !query.trim().isEmpty()) {
    // List<User> results = this.userService.searchByName(query);
    // model.addAttribute("results", results);
    // }
    // return "admin/manage_user/search_user";
    // }

    // }

}
