package com.mightymice.superfleek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm(Model viewModel, String username) {
        viewModel.addAttribute("username", username );
        return "login";
    }

}
