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
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.vtluan.model.Products;
import com.shop.vtluan.model.DTO.ProductsCriteriaDto;
import com.shop.vtluan.repository.ProductRepository;
import com.shop.vtluan.service.spec.ProductSpec;

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

    public Page<Products> getProductsByNameLike(Pageable pageable, String name) {
        return this.productRepository.findAll(ProductSpec.nameLike(name), pageable);
    }

    public Page<Products> getProductsMinPrice(Pageable pageable, Double price) {
        return this.productRepository.findAll(ProductSpec.minPrice(price), pageable);
    }

    public Page<Products> getProductsMaxPrice(Pageable pageable, Double price) {
        return this.productRepository.findAll(ProductSpec.maxPrice(price), pageable);
    }

    public Page<Products> getProductsEqualFactory(Pageable pageable, String factory) {
        return this.productRepository.findAll(ProductSpec.equalFactory(factory), pageable);
    }

    public Page<Products> getProductsmatchFactory(Pageable pageable, List<String> factory) {
        return this.productRepository.findAll(ProductSpec.matchBrand(factory), pageable);
    }

    public Specification<Products> getProductsMatchPrice(List<String> match) {
        Specification<Products> combie = Specification.where(null);
        for (String item : match) {
            double min = 0;
            double max = 0;
            switch (item) {
                case "$lt10":
                    min = 0;
                    max = 10000000;

                    break;
                case "16-20":
                    min = 16000000;
                    max = 20000000;

                    break;
                case "21-25":
                    min = 21000000;
                    max = 25000000;

                    break;
                case "26-30":
                    min = 26000000;
                    max = 30000000;

                    break;
                case "$gt30":
                    min = 30000001;
                    max = 10000000000.00;

                    break;
            }
            if (max != 0 && min != 0) {
                Specification<Products> itemCombie = ProductSpec.matchPrice(min, max);
                combie = combie.and(itemCombie);
            }
        }

        return combie;
    }

    public Page<Products> getCriteriaWithSpec(Pageable pageable, ProductsCriteriaDto productsCriteriaDto) {

        Specification<Products> combie = Specification.where(null);

        if (productsCriteriaDto.getBrand() == null && productsCriteriaDto.getTarget() == null
                && productsCriteriaDto.getPrice() == null) {
            return this.productRepository.findAll(pageable);
        }

        if (productsCriteriaDto.getBrand() != null && productsCriteriaDto.getBrand().isPresent()) {
            Specification<Products> criteriaItem = ProductSpec.matchBrand(productsCriteriaDto.getBrand().get());
            combie = combie.and(criteriaItem);
        }

        if (productsCriteriaDto.getTarget() != null && productsCriteriaDto.getTarget().isPresent()) {
            Specification<Products> criteriaItem = ProductSpec.matchTarget(productsCriteriaDto.getTarget().get());
            combie = combie.and(criteriaItem);
        }

        if (productsCriteriaDto.getPrice() != null && productsCriteriaDto.getPrice().isPresent()) {
            Specification<Products> criteriaItem = getProductsMatchPrice(productsCriteriaDto.getPrice().get());
            combie = combie.and(criteriaItem);
        }

        return this.productRepository.findAll(combie, pageable);
    }

}
