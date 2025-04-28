package com.skooly.backend.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
   private final JwtUtils jwtUtils;
   private final UserDetailsService userDetailsService;
   
   @Override protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		   FilterChain filterChain) throws ServletException, IOException {
	  String authHeader = request.getHeader("Authorization");
	  if(authHeader != null && authHeader.startsWith("Bearer ")){
		 String token = authHeader.substring(7);
		 String username = jwtUtils.getUsernameFromJwtToken(token);
		 if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			if(jwtUtils.validateJwtToken(token)){
			   UsernamePasswordAuthenticationToken authentication =
					   new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			   SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		 }
	  }
	  filterChain.doFilter(request, response);
   }
}