package com.mightymice.superfleek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MakeupController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/home")
    public String dashboard(){
        return "/dashboard";
    }

    @GetMapping("/category/{id}")
    public String productView(@PathVariable long id){
        return "products";
    }


}
