package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.Makeup;
import com.mightymice.superfleek.models.MakeupList;
import com.mightymice.superfleek.models.Review;
import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.MakeupLists;
import com.mightymice.superfleek.repositories.Makeups;
import com.mightymice.superfleek.repositories.Reviews;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MakeupController {
    private MakeupLists makeupLists;
    private Makeups makeups;
    private Reviews reviews;
    private Users users;

    @Autowired
    public MakeupController(Makeups makeups, Reviews reviews, MakeupLists makeupLists, Users users){
        this.makeups = makeups;
        this.reviews = reviews;
        this.makeupLists =makeupLists;
        this.users = users;
    }

    @GetMapping("/")
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


        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            User signedInUser = users.findByUsername(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
            if(signedInUser.getMakeupLists() !=null) {
                MakeupList collection = signedInUser.getMakeupListByNameFromMakeupLists("Collection");
                MakeupList wishList = signedInUser.getMakeupListByNameFromMakeupLists("Wish List");
                viewModel.addAttribute("userCollection", collection);
                viewModel.addAttribute("userWishList", wishList);
            }
        }
        viewModel.addAttribute("makeup", makeup);
        Review review = new Review();
        review.setRating(0);
        viewModel.addAttribute("review", review);


        return "product";
    }


    @PostMapping("/product/{id}")
    public String postReview(@ModelAttribute Review review, @ModelAttribute Makeup makeup, @PathVariable long id) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        review.setUser(user);
        review.setMakeup(makeups.findOne(id));
        reviews.save(review);
        return "redirect:/product/" + id;
    }
    @GetMapping("/search")
    public String queryProducts(HttpServletRequest request, Model viewModel) {
        String query = request.getParameter("query");
        viewModel.addAttribute("query", query);
        return "search";
    }

//    @GetMapping("/search")
//    public String searchProducts() {
//        return "search";
//    }

    @PostMapping("/product/{id}/add/{listName}")
    public String addProductToCollection(@PathVariable long id, @PathVariable String listName){
        User user = new User((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

            MakeupList collection = makeupLists.findByTitleAndUser(listName, user);
            if (!collection.hasMakeup(makeups.findOne(id))) {
                collection.getMakeups().add(makeups.findOne(id));
                makeupLists.save(collection);
            }


//        if(listName.equals("wishlist")){
//            MakeupList collection = makeupLists.findByTitleAndUser("Wish List", user);
//            if (!collection.hasMakeup(makeups.findOne(id))) {
//                collection.getMakeups().add(makeups.findOne(id));
//                makeupLists.save(collection);
//            }
//
//        }
        return "redirect:/product/"+id;
    }

    @PostMapping("/product/{id}/remove/{listName}")
    public String removeProductFromCollection(@PathVariable long id, @PathVariable String listName){
        User user = new User((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        MakeupList collection = makeupLists.findByTitleAndUser(listName, user);
        if (collection.hasMakeup(makeups.findOne(id))) {
            collection.getMakeups().remove(makeups.findOne(id));
            makeupLists.save(collection);
        }
        return "redirect:/product/"+id;
    }


    private Makeup makeupBrandToUpperCase(Makeup makeup){
        String brandName = makeup.getBrand().getName();

        makeup.getBrand().setName(brandName.substring(0, 1).toUpperCase()+brandName.substring(1));
        return makeup;
    }
    private Makeup removeBrandFromMakeupName(Makeup makeup){
        String brandName = makeup.getBrand().getName();
        String makeupName = makeup.getName();
        if(makeupName.contains(brandName)){
            makeupName = makeupName.replace(brandName, "");
            makeup.setName(makeupName);
        }
        return makeup;
    }
    private Makeup prepareMakeup(Makeup makeup){
        makeup = makeupBrandToUpperCase(makeup);
        makeup = removeBrandFromMakeupName(makeup);
        return makeup;

    }


}
