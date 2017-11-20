package com.mightymice.superfleek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MakeupController {

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @GetMapping("/make-profile")
    public String makeProfile(){
        return "make-profile";
    }



//    @GetMapping("/home")
//    @ResponseBody
//    public String dashboard(){
//        return "dashboard";
//    }
//
    @GetMapping("/product/{id}")
    public String productView(@PathVariable long id){
        return "product";
    }


}
