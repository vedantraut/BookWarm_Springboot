package com.vedantraut.bookwarm.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
	
	// Secret Key For Development
//	private static final String SECRET_KEY = "7345762A313F4428472B4B6250655368566D5970337336763979244226452948"; // 256-bit key (32-byte hex)
	
	@Value("${jwt.secret}")
	public String SECRET_KEY;
	
	// Generate token
	public String generateToken(String username) {
		
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, username);
	}
	
	// Create the token
	private String createToken(Map<String, Object> claims, String username) {
		
		return Jwts
				.builder()
				.setClaims(claims)
				.setSubject(username)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // For 10 Hours
				.signWith(getSignKey(), SignatureAlgorithm.HS256)
				.compact(); 
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// Extract username
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	// Generic claim extractor
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	 // Get all claims
	private Claims extractAllClaims(String token) {
		return Jwts
				   .parserBuilder()
				   .setSigningKey(getSignKey())
				   .build()
				   .parseClaimsJws(token)
				   .getBody();
	}
	
	// Validate token
    public boolean isTokenValid(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username)) && !isTokenExpired(token);
    }

    // Check expiry of the token
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    // Check Expiration of token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
