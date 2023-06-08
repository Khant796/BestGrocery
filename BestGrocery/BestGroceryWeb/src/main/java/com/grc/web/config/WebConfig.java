package com.grc.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@Configuration
@EnableWebMvc
@Import(RootConfig.class)
@ComponentScan(basePackages = "com.grc.web.controller")
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public SpringResourceTemplateResolver springResourceTemplateResolver() {
		var tr = new SpringResourceTemplateResolver();
		tr.setPrefix("/views/");
		tr.setSuffix(".html");
		return tr;
	}
	
	@Bean
	public SpringTemplateEngine templateEngine(SpringResourceTemplateResolver resolver) {
		var te = new SpringTemplateEngine();
		te.setTemplateResolver(resolver);
		return te;
	}
	
	@Bean
	public ThymeleafViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		var vr = new ThymeleafViewResolver();
		vr.setTemplateEngine(templateEngine);
		return vr;
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
