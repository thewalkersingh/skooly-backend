package com.skooly.backend.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  // Enables method-level security annotations if needed
public class SecurityConfig {
   @Bean
   public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http
			  .csrf(AbstractHttpConfigurer::disable) // For demonstration; consider enabling CSRF protection in production.
			  .authorizeHttpRequests(auth -> auth
											 // Only teachers can access grading endpoints.
											 .requestMatchers("/api/grades/**").hasRole("TEACHER")
											 // Only students can submit assignments. You can also secure further
											 // submission endpoints if needed.
											 .requestMatchers("/api/submissions/**").hasRole("STUDENT")
											 // Assignments endpoints require authenticated users.
											 .requestMatchers("/api/assignments/**").authenticated()
											 // Other endpoints remain public
											 .anyRequest().permitAll()
									)
			  .httpBasic(Customizer.withDefaults()); // Using HTTP Basic authentication for demonstration.
	  return http.build();
   }
   
   // For demonstration purposes, configure in-memory users.
   // For production, integrate with a user datastore.
   @Bean
   public UserDetailsService userDetailsService() {
	  UserDetails student = User.builder()
								.username("student")
								.password(passwordEncoder().encode("password"))
								.roles("STUDENT")
								.build();
	  UserDetails teacher = User.builder()
								.username("teacher")
								.password(passwordEncoder().encode("password"))
								.roles("TEACHER")
								.build();
	  return new InMemoryUserDetailsManager(student, teacher);
   }
   
   @Bean
   public PasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
   }
}