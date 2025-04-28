package com.skooly.backend.config;
import com.skooly.backend.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SkoolySecurityConfig {
   @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http,
		   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
	  http
			  // Configure CORS using Customizer
			  .cors(Customizer.withDefaults())
			  // Disable CSRF protection
			  .csrf(AbstractHttpConfigurer::disable)
			  // Session management with concurrency and stateless policy
			  .sessionManagement(sessionManagement -> sessionManagement
					  .sessionConcurrency(sessionConcurrency -> sessionConcurrency
							  .maximumSessions(1)
							  .expiredUrl("/login?expired"))
					  .sessionCreationPolicy(
							  SessionCreationPolicy.STATELESS))
			  // Authorization rules
			  .authorizeHttpRequests(authorize -> authorize
					  .requestMatchers("/api/auth/**",
							  "/swagger-ui/**",
							  "/v3/api-docs/**",
							  "/swagger-resources/**",
							  "/swagger-ui.html")
					  .permitAll().anyRequest().authenticated())
			  // Add JWT authentication filter
			  .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	  return http.build();
   }
   
   @Bean public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
   
   @Bean public CorsConfigurationSource corsConfigurationSource() {
	  CorsConfiguration configuration = new CorsConfiguration();
	  configuration.setAllowedOrigins(List.of("http://localhost:3000")); // Allow React frontend
	  configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	  configuration.setAllowedHeaders(List.of("*"));
	  configuration.setAllowCredentials(true); // allow cookies/auth if needed
	  
	  UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	  source.registerCorsConfiguration("/**", configuration);
	  
	  return source;
   }
   
   @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
		   throws Exception {
	  return authConfig.getAuthenticationManager();
   }
}