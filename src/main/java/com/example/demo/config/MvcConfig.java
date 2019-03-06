package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.PathMatcher;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
/*
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
       registry.addViewController("/login").setViewName("hello");
        registry.addViewController("/error").setViewName("error");
    }*/

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		 configurer
         .setUseSuffixPatternMatch(true)
         .setUseTrailingSlashMatch(false)
         .setUseRegisteredSuffixPatternMatch(true)
         .setPathMatcher(antPathMatcher())
         .setUrlPathHelper(urlPathHelper())
         .addPathPrefix("/",
                 HandlerTypePredicate.forAnnotation(RestController.class));
	}
    
	 @Bean
	    public UrlPathHelper urlPathHelper() {
			return new UrlPathHelper(); 
	        //...
	    }

	    @Bean
	    public PathMatcher antPathMatcher() {
	        //...
	    	return null;
	    }
    

}