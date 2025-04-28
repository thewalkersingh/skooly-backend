package com.skooly.backend.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
   @Bean
   public OpenAPI skoolyOpenAPI() {
	  final String securitySchemeName = "bearerAuth";
	  return new OpenAPI()
			  .info(new Info()
					  .title("School Management API")
					  .description("Backend APIs for School Management System 🚀")
					  .version("v1.0"))
			  .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
			  .components(new io.swagger.v3.oas.models.Components()
					  .addSecuritySchemes(securitySchemeName, new SecurityScheme()
							  .name(securitySchemeName)
							  .type(SecurityScheme.Type.HTTP)
							  .scheme("bearer")
							  .bearerFormat("JWT")));
   }
}