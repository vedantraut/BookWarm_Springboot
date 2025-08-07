package com.vedantraut.bookwarm.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.vedantraut.bookwarm.security.JWTAuthFilter;

@Configuration
public class AppConfig {
	
	@Autowired
	JWTAuthFilter jwtAuthFilter;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception  {
		
//		http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
//				.requestMatchers("/**").permitAll()
////				.anyRequest().permitAll()
//			);
		
		// Adds JWT Authentication for all the APIS excluding login and register
		http.csrf(csrf -> csrf.disable())
//			.authorizeHttpRequests(auth -> auth
//				.requestMatchers("/api/users/login", "/api/users/register").permitAll()
//				.anyRequest().authenticated()
//			)
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//			.build();
		
		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public WebMvcConfigurer CorsConfigurer() {
		
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:4200")
						.allowedMethods("*")
						.allowedHeaders("*");
			}
		};
		
	}
}
