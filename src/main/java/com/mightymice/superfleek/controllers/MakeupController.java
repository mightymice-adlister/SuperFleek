package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.Makeup;
import com.mightymice.superfleek.models.Review;
import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.Makeups;
import com.mightymice.superfleek.repositories.Reviews;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MakeupController {
    private Users users;
    private Makeups makeups;
    private Reviews reviews;
    @Autowired
    public MakeupController(Makeups makeups, Reviews reviews, Users users){
        this.makeups = makeups;
        this.reviews = reviews;
        this.users =users;
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
    public String productView(@PathVariable long id, Model viewModel){
        Makeup makeup = makeups.findOne(id);
        viewModel.addAttribute("makeup", makeup);
        Review review = new Review();
        review.setRating(0);
        viewModel.addAttribute("review", review);

        return "product";
    }

    @PostMapping("/product/{id}")
    public String postReview(@ModelAttribute Review review, @ModelAttribute Makeup makeup, @PathVariable long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        review.setUser(user);
        review.setMakeup(makeups.findOne(id));
        reviews.save(review);
        return "redirect:/product/"+id;
    }


}
