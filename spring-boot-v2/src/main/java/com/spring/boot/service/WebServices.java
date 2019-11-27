package com.spring.boot.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.model.JsonInputModel;

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
	
	@PostMapping(value="/jsoninput/{pv}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> jsonInput(HttpServletRequest request , 
			@RequestBody JsonInputModel requestBody, 
			@PathVariable("pv") String pathVariable, 
			@RequestParam("rp") String requestParam)
	{
		System.out.println("Request key : " + requestBody.getInputKey());
		System.out.println("Request value : " + requestBody.getInputValue());
		if(requestBody.getInputKey().isEmpty() || pathVariable.isEmpty() || requestParam.isEmpty() )
			throw new RuntimeException("input param is empty");
		return ResponseEntity.ok().build();
	}
}

