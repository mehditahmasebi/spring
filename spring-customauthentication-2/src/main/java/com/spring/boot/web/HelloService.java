package com.spring.boot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloService {
	
	@GetMapping
	public String get()
	{
		return "Hello there!";
	}

}
