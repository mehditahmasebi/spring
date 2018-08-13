package com.spring.boot.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {
	
	@RequestMapping("/public/jsp/hello")
	public String hellojsp(Model model){
		System.out.println("JSP Page requested");
		return "hello";
	}

}
