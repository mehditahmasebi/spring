package com.spring.boot.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/test")
public class WebServices {

	@GetMapping
	public String helloWorld()
	{
		return "It's OK!";
	}
	@PostMapping
	public String helloWorldPost()
	{
		return "It's OK!";
	}
}
