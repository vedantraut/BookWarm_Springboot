package com.vedantraut.bookwarm.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
	
	@Autowired
	CustomUserDetailsService userDetailsService;
	
	@Autowired
	JWTService jwtservice;
	
	/**
	 * What does doFilterInternal is doing?
	 * 
	→ [User sends request with JWT] 
	 → Filter sees Authorization header →
	   → Extracts token →
	     → Extracts email from token →
	       → Loads user from DB →
	         → Validates token again →
	           → Creates auth object →
	             → Sets into SecurityContext →
	               → Request goes to Controller ✅
	 */
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authHeader = request.getHeader("Authorization"); // Filter sees Authorization header
		final String token;
		final String userEmail;
		
		
		if(authHeader == null || !authHeader.startsWith("Bearer")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		token = authHeader.substring(7); // Skip "Bearer " // Extracts token
		userEmail = jwtservice.extractUsername(token); // Extracts email from token
		
		if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			UserDetails userdetails = userDetailsService.loadUserByUsername(userEmail); // Loads user from DB
			
			// Validates token again
			if(jwtservice.isTokenValid(token, userdetails.getUsername())) {
				
				// Creates auth object
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				// Sets the user as authenticated and marks user as logged in
                SecurityContextHolder.getContext().setAuthentication(authToken); // Sets into SecurityContext
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
