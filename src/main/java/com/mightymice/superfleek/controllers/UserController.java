package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.Look;
import com.mightymice.superfleek.models.MakeupList;
import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.models.UserWithRoles;
import com.mightymice.superfleek.repositories.Looks;
import com.mightymice.superfleek.repositories.MakeupLists;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private Looks looks;
    private MakeupLists makeupLists;
    @Autowired
    public UserController(Users users, PasswordEncoder passwordEncoder, Looks looks, MakeupLists makeupLists){
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.looks = looks;
        this.makeupLists=makeupLists;
    }

    @GetMapping("/sign-up")
    public String RegisterView(Model viewModel){

        User user = new User();
        viewModel.addAttribute("user", user);
        return"sign-up";
    }

    @PostMapping("/sign-up")
    public String RegisterUser(@Valid User user, Errors validation, Model viewModel){


        if(!user.getConfirmPassword().equals(user.getPassword())){
            validation.rejectValue("confirmPassword", "user.confirmPassword", "Passwords don't match");

        }

        if(users.findByUsername(user.getUsername()) != null){
            validation.rejectValue("username","user.username","That username already exists.");
        }


        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return"/sign-up";
        }

//        user.setConfirmPassword("");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setHasLoggedIn(false);
        users.save(user);
        authenticate(user);
        return "redirect:/profile";
    }

    @GetMapping("/profile/{username}")
    public String profileView(@PathVariable String username, Model viewModel){
        User user = users.findByUsername(username);
        Look newLook = new Look();
        MakeupList collection = user.getCollectionFromMakeupList();
        viewModel.addAttribute("look",newLook);
        viewModel.addAttribute("collection", collection);
        viewModel.addAttribute("profilePic", user.getProfilePic());
        viewModel.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/profile")
    public String forwardUserToProfileView(){
        String username;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null){
            username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            if(!users.findByUsername(username).isHasLoggedIn()){
                return "redirect:/make-profile";
            }
        return "redirect:/profile/"+username;
        }
            return "redirect:/login";

    }

    @GetMapping("/make-profile")
    public String makeProfile(Model vmodel){
        User user = new User();
        vmodel.addAttribute("user", user);
        return "make-profile";
    }

    @PostMapping("/make-profile")
    public String makeProfile(@ModelAttribute User user){
        User signedInUser = users.findByUsername(((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        if(user.getLookList()!=null){

            List<Look> newLooks = user.getLookList();
            newLooks.get(0).setUser(signedInUser);
            newLooks.get(0).setProfilePic(true);
            signedInUser.getLookList().add(newLooks.get(0));
            looks.save(newLooks.get(0));
        } else {
            System.out.println("getLookList is null");
        }
        if(user.getBio() != null){
            signedInUser.setBio(user.getBio());
        } else {
            System.out.println("get bio is null");
        }
        signedInUser.setHasLoggedIn(true);
        ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).setHasLoggedIn(true);
        signedInUser.setConfirmPassword(signedInUser.getPassword());
        List<MakeupList> initialMULists = signedInUser.getMakeupLists();
        MakeupList collection = new MakeupList("Collection", signedInUser);
        MakeupList wishList = new MakeupList("Wish List", signedInUser);
        initialMULists.add(collection);
        initialMULists.add(wishList);
        makeupLists.save(wishList);
        makeupLists.save(collection);
        signedInUser.setMakeupLists(initialMULists);

        users.save(signedInUser);
        return "redirect:/profile";

    }

    @PostMapping("/look/add/")
    public String uploadLook(@ModelAttribute Look look){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        look.setUser(user);
        look.setProfilePic(false);
        looks.save(look);
        return "redirect:/profile";
    }

    @PostMapping("/look/{id}/makeprofilepic")
    public String makeProfilePic(@PathVariable Long id){
        List<Look> looksList =looks.findAllByUser(looks.findOne(id).getUser());
        Look newProfilePic = looks.findOne(id);
        for(Look look:looksList){
         if(look.getId() != newProfilePic.getId()){
             look.setProfilePic(false);
         }
        }
        newProfilePic.setProfilePic(true);
        looks.save(newProfilePic);
        return "redirect:/profile";
    }

    @GetMapping("/look/{id}")
    public String showLook(@PathVariable Long id, Model viewModel){
        Look look = looks.findOne(id);
        viewModel.addAttribute("look", look);
        return "look";
    }

    @PostMapping("/look/{id}/update")
    public String updateLook(@PathVariable Long id, @ModelAttribute Look look){
        Look lookToUpdate = looks.findOne(id);
        if(!look.getDescription().isEmpty()){
            lookToUpdate.setDescription(look.getDescription());
        }
        if(look.getMakeups() != null){
            lookToUpdate.setMakeups(look.getMakeups());
        }
        if(!look.getTitle().isEmpty()){
            lookToUpdate.setTitle(look.getTitle());
        }
        looks.save(lookToUpdate);
        return "redirect:/look/"+id;
    }


    private void authenticate(User user) {
        // Notice how we're using an empty list for the roles
        UserDetails userDetails = new UserWithRoles(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(auth);
    }


}
