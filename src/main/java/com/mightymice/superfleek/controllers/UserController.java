package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.repositories.Users;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sign-up")
    public String RegisterView(){
        return"sign-up";
    }

    @PostMapping("/sign-up")
    public String RegisterUser(){
        return "redirect:/login";
    }

//    @GetMapping("/{username}")
//    @ResponseBody
//    public String profileView(@PathVariable String username){
//        return "profile";
//    }
}
