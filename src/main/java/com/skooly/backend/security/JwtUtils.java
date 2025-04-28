package com.skooly.backend.security;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtils {
   private final String jwtSecret = "verysecretkeyverysecretkeyverysecretkey"; // 🔥 256-bit
   private final int jwtExpirationMs = 86400000; // 24 hours
   
   // Updated to accept username + role
   public String generateJwtToken(String username, String role) {
	  Map<String, Object> claims = new HashMap<>();
	  claims.put("role", role);
	  
	  return Jwts.builder()
				 .setClaims(claims)
				 .setSubject(username)
				 .setIssuedAt(new Date())
				 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				 .signWith(getSigningKey())
				 .compact();
   }
   
   private Key getSigningKey() {
	  return Keys.hmacShaKeyFor(jwtSecret.getBytes());
   }
   
   public String getUsernameFromJwtToken(String token) {
	  return Jwts.parserBuilder()
				 .setSigningKey(getSigningKey())
				 .build()
				 .parseClaimsJws(token)
				 .getBody()
				 .getSubject();
   }
   
   // New method: extract Role
   public String getRoleFromJwtToken(String token) {
	  return (String) Jwts.parserBuilder()
						  .setSigningKey(getSigningKey())
						  .build()
						  .parseClaimsJws(token)
						  .getBody()
						  .get("role");
   }
   
   public boolean validateJwtToken(String token) {
	  try{
		 Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
		 return true;
	  } catch(JwtException e){
		 return false;
	  }
   }
}