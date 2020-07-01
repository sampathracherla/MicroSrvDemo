package com.msdemo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class MSDemoFilter implements WebFluxConfigurer {

	@Value("${msdemo-allow-methods}")
	public String msDemoAllowMethods;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowCredentials(true).allowedOrigins("*").allowedHeaders("*").allowedMethods(msDemoAllowMethods);
	}
	
	@Bean
	public CorsWebFilter corsWebFilter() {
		CorsConfiguration cors = new CorsConfiguration();
		cors.setAllowCredentials(true);
		cors.addAllowedHeader("*");
		cors.addAllowedMethod("*");
		cors.addAllowedOrigin("*");
		UrlBasedCorsConfigurationSource conf = new UrlBasedCorsConfigurationSource();
		conf.registerCorsConfiguration("/**", cors);
		return new CorsWebFilter(conf);
	}



}

