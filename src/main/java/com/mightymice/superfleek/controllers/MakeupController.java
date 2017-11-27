package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.Look;
import com.mightymice.superfleek.models.Makeup;
import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.Makeups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MakeupController {
    private Makeups makeups;
    @Autowired
    public MakeupController(Makeups makeups){
        this.makeups = makeups;
    }

    @GetMapping("/")
    @ResponseBody
    public String index(){
        return "index";
    }

    @GetMapping("/makeup.json")
    public @ResponseBody Iterable<Makeup> viewAllMakeupInJSONFormat(){
        return makeups.findAll();
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
