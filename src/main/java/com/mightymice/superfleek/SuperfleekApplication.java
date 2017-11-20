package com.mightymice.superfleek;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperfleekApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SuperfleekApplication.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SuperfleekApplication.class);
	}
}
