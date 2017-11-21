package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserController(Users users, PasswordEncoder passwordEncoder){
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String RegisterView(Model viewModel){
        User user = new User();
        viewModel.addAttribute("user", user);
        return"sign-up";
    }

    @PostMapping("/sign-up")
    public String RegisterUser(@Valid User user, Errors validation, Model viewModel){
        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return"/sign-up";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String profileView(@PathVariable String username){
        return "profile";
    }
    @GetMapping("/profile")
    public String forwardUserToProfileView(){
        String username = "";
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null){
            username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return "redirect:/profile/"+username;
        }
            return "redirect:/login";

    }
}
