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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.shop.vtluan.model.Role;
import com.shop.vtluan.model.User;
import com.shop.vtluan.service.RoleService;
import com.shop.vtluan.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    UserService userService;
    RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("admin/users")
    public String getHomeManageUser(Model model, @RequestParam("search") Optional<String> search,
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

        }
        Pageable pageable = PageRequest.of(page - 1, 5);
        Page<User> usersPage = this.userService.getUsers(pageable);
        List<User> users = usersPage.getContent();
        model.addAttribute("users", users);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPage", usersPage.getTotalPages());
        // search
        if (search.isPresent()) {
            model.addAttribute("search", search);
            List<User> results = this.userService.searchByName(search.get());
            model.addAttribute("results", results);

        }
        return "admin/manage_user/table_user";
    }

    @GetMapping("admin/user/create")
    public String getCreateUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/manage_user/create_user";
    }

    @PostMapping("/admin/user/created")
    public String postCreateUser(@RequestParam("file") MultipartFile file, @ModelAttribute @Valid User user,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                System.out.println(">>>" + fieldError.getField() + ">>>" + fieldError.getDefaultMessage());
            }
            return "admin/manage_user/create_user";
        }

        // upload file
        String image = this.userService.upLoadFile(file);
        Role role = this.roleService.findRolebyName(user.getRole().getName());
        user.setRole(role);
        user.setPassword(this.userService.encodePassword(user.getPassword()));
        user.setImage(image != null ? image : "");
        this.userService.saveUser(user);

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUser(@PathVariable("id") long id, Model model) {
        Optional<User> user = this.userService.findUser(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        return "admin/manage_user/delete_user";
    }

    @PostMapping("/admin/user/deleted")
    public String postMethodName(@ModelAttribute User user) {
        this.userService.removeUser(user.getId());

        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String getEditUser(@PathVariable("id") long id, Model model) {
        Optional<User> user = this.userService.findUser(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        return "admin/manage_user/edit_user";
    }

    @PostMapping("/admin/user/edited")
    public String postEditUser(@ModelAttribute User user, @RequestParam("file") MultipartFile file) throws IOException {
        User currentUser = new User();
        Role existingRole = this.roleService.findRolebyName(user.getRole().getName());
        if (user != null && existingRole != null) {
            currentUser.setId(user.getId());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(this.userService.encodePassword(user.getPassword()));
            currentUser.setPhone_number(user.getPhone_number());
            currentUser.setName(user.getName());
            currentUser.setRole(existingRole);

            if (file.isEmpty()) {
                currentUser.setImage(user.getImage());
                System.out.println("run here");
            } else {
                this.userService.upLoadFile(file);
                this.userService.deleteFile(user.getImage());
                currentUser.setImage(file.getOriginalFilename());
            }

            System.out.println(">>>>> run here: " + currentUser.toString());
            this.userService.saveUser(currentUser);
        }
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/user/view/{id}")
    public String getViewUser(@PathVariable("id") long id, Model model) {
        Optional<User> user = this.userService.findUser(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
        }
        return "admin/manage_user/view_user";
    }

}
