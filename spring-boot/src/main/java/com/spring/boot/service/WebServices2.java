package com.spring.boot.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class WebServices2 {
	
	@GetMapping
	public String helloWorld()
	{
		return "It's OK, for advance usage(/admin) and (/user)";
	}

	@GetMapping("/user")
	public String helloWorldAnyRole()
	{
		return "It's OK for ROLE_USER!";
	}
	
	@GetMapping("/admin")
	@Secured("ROLE_ADMIN")
	public String helloWorldOnlyRole()
	{
		return "It's OK for only ROLE_ADMIN!";
	}
}
