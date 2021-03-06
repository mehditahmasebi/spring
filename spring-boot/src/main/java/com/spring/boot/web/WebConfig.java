package com.spring.boot.web;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled=true,
		securedEnabled=true,
		jsr250Enabled=true)
public class WebConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {		
		http
			.authorizeRequests()
				.antMatchers("/public/**").permitAll()
				.anyRequest().authenticated()
			.and().csrf().disable();		
	}
	
	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		//to accept dot in REST string parameter
		configurer.setUseSuffixPatternMatch(false);
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
	public InternalResourceViewResolver jspViewResolver() {
	    InternalResourceViewResolver resolver= new InternalResourceViewResolver();
	    resolver.setPrefix("/jsp/");
	    resolver.setSuffix(".jsp");
	    return resolver;
	}  
	
	@Bean
	public ErrorPageFilter errorPageFilter() {
	    return new ErrorPageFilter();
	}

	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize("20MB");
		factory.setMaxRequestSize("5000KB");
		return factory.createMultipartConfig();
	}
//
//	@Bean
//	public FilterRegistrationBean disableSpringBootErrorFilter(ErrorPageFilter filter) {
//	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//	    filterRegistrationBean.setFilter(filter);
//	    filterRegistrationBean.setEnabled(false);
//	    return filterRegistrationBean;
//	}

}
