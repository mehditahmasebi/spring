package com.spring.boot.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.spring.boot.service.XSSFilter;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
			.authorizeRequests()
				.anyRequest().permitAll()
			.and().csrf().disable();
	}
	
	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowCredentials(true)
        .allowedHeaders("*")
        .allowedMethods("GET, POST, PATCH, PUT, DELETE, OPTIONS")
        .allowedOrigins("*");
    }
	
	@Bean
	public FilterRegistrationBean xssPreventFilter() {
	    FilterRegistrationBean registrationBean = new FilterRegistrationBean();

	    registrationBean.setFilter(new XSSFilter());
	    registrationBean.addUrlPatterns("/*");

	    return registrationBean;
	}
}
