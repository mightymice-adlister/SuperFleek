package com.mightymice.superfleek.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @GetMapping("/register")
    public String RegisterView(){
        return"/register";
    }

    @PostMapping("/register")
    public String RegisterUser(){
        return "redirect:/login";
    }

    @GetMapping("/{username}")
    public String profileView(@PathVariable String username){
        return "/profile";
    }
}
