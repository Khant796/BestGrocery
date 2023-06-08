package com.grc.web.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;

public class MvcInitializer implements WebApplicationInitializer {

	
	private String FOLDER = "/resources/image/";
	private int MAX = 10 * 1024 * 1024;
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		
		AnnotationConfigWebApplicationContext web = new AnnotationConfigWebApplicationContext();
		web.register(WebConfig.class);
		web.setServletContext(servletContext);
		web.refresh();
		
		var dispatcher = servletContext.addServlet("mvc", new DispatcherServlet(web));
		dispatcher.addMapping("/");
		dispatcher.setLoadOnStartup(1);
		
		ServletRegistration.Dynamic h2Servlet = servletContext.addServlet("h2-console", new JakartaWebServlet());
		h2Servlet.setLoadOnStartup(2);
		h2Servlet.addMapping("/console/*");
		
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(FOLDER, MAX, MAX * 2, MAX / 2);
		dispatcher.setMultipartConfig(multipartConfigElement);
	}

}
