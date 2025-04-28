package com.skooly.backend.controller;
import com.skooly.backend.entity.Admin;
import com.skooly.backend.exception.UserAlreadyExistsException;
import com.skooly.backend.repository.AdminRepository;
import com.skooly.backend.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
   private final AuthenticationManager authenticationManager;
   private final AdminRepository adminRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtUtils jwtUtils;
   
   @PostMapping("/login")
   public String login(@RequestParam String username, @RequestParam String password) {
	  try{
		 Authentication authentication = authenticationManager.authenticate(
				 new UsernamePasswordAuthenticationToken(username, password));
		 Admin admin = adminRepository.findByUsername(username)
									  .orElseThrow(() -> new RuntimeException("Admin not found"));
		 return jwtUtils.generateJwtToken(username, admin.getRole().name());
	  } catch(AuthenticationException e){
		 throw new RuntimeException("Invalid username/password");
	  }
   }
   
   @PostMapping("/register")
   public String register(@RequestParam String username, @RequestParam String password) {
	  if(adminRepository.findByUsername(username).isPresent()){
		 throw new UserAlreadyExistsException("Username already exists!");
	  }
	  // Validate username and password
	  if(username.isEmpty() || password.isEmpty()){
		 throw new IllegalArgumentException("Username and password cannot be empty.");
	  }
	  Admin admin = new Admin();
	  admin.setUsername(username);
	  admin.setPassword(passwordEncoder.encode(password));
	  adminRepository.save(admin);
	  
	  return "Admin registered successfully!";
   }
}