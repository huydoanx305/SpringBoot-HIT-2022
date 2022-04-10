package com.hit.buoi4.controller;

import com.hit.buoi4.entity.User;
import com.hit.buoi4.model.mapper.UserMapper;
import com.hit.buoi4.repository.UserRepository;
import com.hit.buoi4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    // Hiển thị trang đăng nhập
    @GetMapping({"/","/login"})
    public String getUser() {
        return "index";
    }
    // Đăng nhập
    @PostMapping("/login")
    public String login(Model model, @ModelAttribute(name = "User") User user) {
        if(userRepository.findAll().contains(user)){
            return "redirect:/store";
        }
        model.addAttribute("errorMessage","Username or password incorrect !");
        return "index";
    }

    //Hiển thị trang Store
    @GetMapping({"/store"})
    public String getStore(Model model) {
        List<User> users =  userRepository.findAll();
        model.addAttribute("Users",users);
        return "store";
    }

    // Hiển thị trang đăng kí
    @GetMapping("/signup")
    public String getSignUp() {
        return "SignUp";
    }
    // Đăng kí
    @PostMapping("/signup")
    public String signUp(@ModelAttribute(name = "User") User user, Model model) {
        if(userService.findAllUser().contains(UserMapper.toUserDto(user))){
            model.addAttribute("errorMessageSignUp","Username already exists");
            return "SignUp";
        }
        userRepository.save(user);
        return "redirect:/login";
    }

    // Hiển thị ra trang edit và lấy thông tin
    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        Optional<User> user = userRepository.findById(id);
        model.addAttribute("User", user.get());
        model.addAttribute("oldUser", user.get().getUsername());
        return "edit";
    }
    // Edit
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute User user, @ModelAttribute(name = "oldUsername") String oldUser, RedirectAttributes ra) {
        if(userService.findAllUser().contains(UserMapper.toUserDto(user)) && !user.getUsername().equals(oldUser))   {
            ra.addFlashAttribute("errorMessageEdit","Username already exists");
            return "redirect:/edit/" + user.getId();
        }
        userRepository.save(user);
        return "redirect:/store";
    }

    // Hiển thị ra trang xác nhận xóa
    @GetMapping("/delete/{id}")
    public String getDelete(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }
    // Xóa
    @GetMapping("/delete")
    public String successfulDelete(@ModelAttribute(name = "id") Long id) {
        userRepository.deleteById(id);
        return "redirect:/store";
    }
}