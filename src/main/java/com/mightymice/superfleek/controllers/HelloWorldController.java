package com.mightymice.superfleek.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloWorldController {

	@GetMapping("/login")
	public String helloSignIn() {
		return "login";
	}

	@GetMapping("/sign-up")
	public String helloSignup() {
		return "sign-up";
	}
}
