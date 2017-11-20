package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;

    public UserController(Users users){
        this.users = users;
    }

    @GetMapping("/sign-up")
    public String RegisterView(Model viewModel){
        viewModel.addAttribute("user", new User());
        return"sign-up";
    }

    @PostMapping("/sign-up")
    public String RegisterUser(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String profileView(@PathVariable String username){
        return "profile";
    }
}
