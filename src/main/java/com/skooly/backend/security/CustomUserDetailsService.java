package com.skooly.backend.security;
import com.skooly.backend.entity.Admin;
import com.skooly.backend.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private final AdminRepository adminRepository;
   
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  Admin admin = adminRepository
			  .findByUsername(username)
			  .orElseThrow(() -> new UsernameNotFoundException("Admin Not Found"));
	  return new User(admin.getUsername(), admin.getPassword(), Collections.emptyList());
   }
}