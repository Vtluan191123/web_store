package com.shop.vtluan.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.User;
import com.shop.vtluan.repository.ProductRepository;

import jakarta.servlet.ServletContext;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ServletContext context;

    public ProductService(ProductRepository productRepository, ServletContext context) {
        this.productRepository = productRepository;
        this.context = context;
    }

    public void saveProducts(Products product) {
        this.productRepository.save(product);
    }

    public Page<Products> getAllProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public Optional<Products> getProductsById(long id) {
        return this.productRepository.findById(id);
    }

    public Page<Products> getProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    public String upLoadFile(MultipartFile file) {

        String filename = "";
        try {
            String pathAdminFile = context.getRealPath("/resources/admin/image/");
            String pathClientFile = context.getRealPath("/resources/client/image/");
            System.out.println("runhere >>> " + pathAdminFile);
            System.out.println("runhere >>> " + pathClientFile);

            // Tạo thư mục nếu chưa tồn tại
            File directoryAdmin = new File(pathAdminFile);
            if (!directoryAdmin.exists()) {
                directoryAdmin.mkdirs();
            }
            File directoryClient = new File(pathAdminFile);
            if (!directoryClient.exists()) {
                directoryClient.mkdirs();
            }

            // Lấy tên file gốc từ MultipartFile
            filename = file.getOriginalFilename();

            // Kiểm tra nếu tên file không phải null hoặc rỗng
            if (filename != null && !filename.isEmpty()) {
                // Tạo file mới với đường dẫn và tên file
                File adminFile = new File(directoryAdmin, filename);
                File clientFile = new File(directoryClient, filename);
                // Lưu file lên hệ thống
                file.transferTo(adminFile);
                // file.transferTo(clientFile);

                // Trả về đường dẫn lưu file
            } else {
                throw new IllegalStateException("Tên file không hợp lệ!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public Optional<Products> findProduct(long id) {
        return this.productRepository.findById(id);
    }

    public void removeProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void deleteFile(String image) throws IOException {
        String pathRoot = context.getRealPath("/resources/admin/image/" + image);
        Path path = Paths.get(pathRoot);
        Files.delete(path);
    }

    public List<Products> searchByNameproduct(String string) {
        return this.productRepository.findByNameContainingIgnoreCase(string);
    }

}
