package com.mightymice.superfleek.controllers;

import com.mightymice.superfleek.models.Look;
import com.mightymice.superfleek.models.User;
import com.mightymice.superfleek.repositories.Looks;
import com.mightymice.superfleek.repositories.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    private Users users;
    private PasswordEncoder passwordEncoder;
    private Looks looks;
    @Autowired
    public UserController(Users users, PasswordEncoder passwordEncoder, Looks looks){
        this.users = users;
        this.passwordEncoder = passwordEncoder;
        this.looks = looks;
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

        if(validation.hasErrors()){
            viewModel.addAttribute("errors", validation);
            viewModel.addAttribute("user", user);
            return"/sign-up";
        }
//        user.setConfirmPassword("");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setHasLoggedIn(false);
        users.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile/{username}")
    public String profileView(@PathVariable String username, Model viewModel){
        viewModel.addAttribute("user", users.findByUsername(username));
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

        users.save(signedInUser);
        return "redirect:/profile";

    }
}
