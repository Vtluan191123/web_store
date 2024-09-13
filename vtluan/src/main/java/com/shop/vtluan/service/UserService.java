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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.vtluan.model.User;
import com.shop.vtluan.model.DTO.UserDto;
import com.shop.vtluan.repository.RoleRepository;
import com.shop.vtluan.repository.UserRepository;

import jakarta.servlet.ServletContext;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ServletContext context;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ServletContext context,
            RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.context = context;
        this.roleRepository = roleRepository;
    }

    public Page<User> getUsers(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public void saveUser(User user) {
        this.userRepository.save(user);
    }

    public void removeUser(long id) {
        this.userRepository.deleteById(id);
    }

    public Optional<User> findUser(long id) {
        return this.userRepository.findById(id);
    }

    public String encodePassword(String password) {
        return this.passwordEncoder.encode(password);
    }

    public List<User> searchByName(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public String upLoadFile(MultipartFile file) {
        String filename = "";
        try {
            String pathFile = context.getRealPath("/resources/admin/image/");
            System.out.println("runhere >>> " + pathFile);

            // Tạo thư mục nếu chưa tồn tại
            File directory = new File(pathFile);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Lấy tên file gốc từ MultipartFile
            filename = file.getOriginalFilename();

            // Kiểm tra nếu tên file không phải null hoặc rỗng
            if (filename != null && !filename.isEmpty()) {
                // Tạo file mới với đường dẫn và tên file
                File destinationFile = new File(directory, filename);

                // Lưu file lên hệ thống
                file.transferTo(destinationFile);
                Path path = destinationFile.toPath();
                Files.delete(path);

                // Trả về đường dẫn lưu file
            } else {
                throw new IllegalStateException("Tên file không hợp lệ!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }

    public User registerDTOtoUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPhone_number(userDto.getPhone_number());
        user.setPassword(encodePassword(userDto.getPassword()));
        user.setRole(this.roleRepository.findByName("USER"));
        user.setImage("normal_avt.png");
        return user;
    }

    public User getUserByEmail(String name) {
        return this.userRepository.findByEmail(name);
    }

    public void deleteFile(String image) throws IOException {
        String pathRoot = context.getRealPath("/resources/admin/image/" + image);
        Path path = Paths.get(pathRoot);
        Files.delete(path);
    }

    public Boolean checkEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public User getUserByToken(String token) {
        return this.userRepository.findByToken(token);
    }

}
