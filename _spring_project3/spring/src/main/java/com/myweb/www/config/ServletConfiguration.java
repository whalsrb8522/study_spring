package com.myweb.www.config;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan(basePackages = {"com.myweb.www.controller", "com.myweb.www.handler"})
public class ServletConfiguration implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// resource 및 upload 경로 설정
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/upload/**").addResourceLocations("E:\\Workspace\\Spring\\_myweb\\_java\\fileUpload");
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// prefix(/WEB-INF/views), suffix(.jsp)
		WebMvcConfigurer.super.configureViewResolvers(registry);
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		registry.viewResolver(viewResolver);
	}
	
	// multipartResolver 설정
	
	
}
