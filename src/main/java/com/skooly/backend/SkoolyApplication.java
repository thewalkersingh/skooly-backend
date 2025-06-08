package com.skooly.backend;
import io.swagger.v3.oas.models.annotations.OpenAPI31;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@OpenAPI31
@SpringBootApplication
public class SkoolyApplication {
   private final Environment env;
   
   @Value("${app.allowed.origins}")
   private String[] allowedOrigins;
   
   @Autowired
   public SkoolyApplication(Environment env) {
	  this.env = env;
   }
   
   public static void main(String[] args) {
	  SpringApplication.run(SkoolyApplication.class, args);
   }
   
   @PostConstruct
   public void logStartupInfo() {
	  System.out.println(">>> ACTIVE PROFILE: " + Arrays.toString(env.getActiveProfiles()));
	  System.out.println(">>> Allowed origins: " + Arrays.toString(allowedOrigins));
   }
}