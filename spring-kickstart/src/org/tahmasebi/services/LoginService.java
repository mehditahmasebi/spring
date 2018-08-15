package org.tahmasebi.services;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/login")
public class LoginService {

	@Autowired
	private CustomAuthenticationProvider provider; 
	
	@GetMapping("/{username}/{password}")
	public ResponseEntity<Map<String, String>> login(@PathVariable String username,@PathVariable String password ,HttpServletRequest request)
	{
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authenticate = provider.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		request.getSession().setAttribute("username", username);
		Map<String, String> result = new HashMap<>();
		result.put(username, new String(Base64.getEncoder().encode(password.getBytes())));
		ResponseEntity<Map<String, String>> finalResult = new ResponseEntity<>(result,HttpStatus.OK);
		return finalResult;
	}
}
