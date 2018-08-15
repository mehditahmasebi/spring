package com.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringbootApplication extends SpringBootServletInitializer {
	
	/**
	 * tomcat deployment needed
	 */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootApplication.class);
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
		System.out.println("Spring boot application started!");
		
	}
}
