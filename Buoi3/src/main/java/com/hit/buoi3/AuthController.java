package com.hit.buoi3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {
    @Autowired
    Store store;

    @Value("${error.message}")
    private String errorMessage;

    @GetMapping({"/","/login"})
    public String getUser() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute(name = "User") User user) {
        List<User> users = store.getAllUser();
        for(User u : users) {
            if(u.equals(user)){
                return "redirect:/store";
            }
        }
        model.addAttribute("errorMessage",errorMessage);
        return "index";
    }

    @GetMapping({"/store"})
    public String getStore(Model model) {
        List<User> users = store.getAllUser();
        model.addAttribute("Users",users);
        return "store";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "SignUp";
    }

    @PostMapping("")
    public String signUp(@ModelAttribute(name = "User") User user) {
        store.addUser(user);
        return "redirect:/";
    }

}
