package com.skooly.backend.config;
import com.skooly.backend.dto.SchoolDTO;
import com.skooly.backend.service.SchoolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
   private final SchoolService schoolService;
   
   public DataSeeder(SchoolService schoolService) {
	  this.schoolService = schoolService;
   }
   
   @Override
   public void run(String... args) throws Exception {
	  // Check if data already exists to avoid re-seeding on every restart
	  if(schoolService.getAllSchools().isEmpty()){
		 // Create some sample school data
		 SchoolDTO school1 = SchoolDTO.builder()
									  .name("Green Valley Public School")
									  .address("123, Green Street, Baramati")
									  .contactNumber("1234567890")
									  .email("contact@greenvalley.edu.in")
									  .schoolType("CBSE")
									  .studentCount(500)
									  .build();
		 
		 SchoolDTO school2 = SchoolDTO.builder()
									  .name("Sunrise International School")
									  .address("456, Sunrise Road, Pune")
									  .contactNumber("0987654321")
									  .email("info@sunrise.edu.in")
									  .schoolType("ICSE")
									  .studentCount(750)
									  .build();
		 
		 schoolService.saveSchool(school1);
		 schoolService.saveSchool(school2);
		 
		 // You can add more seed data or even seed related entities later.
		 System.out.println("Sample School data seeded successfully!");
	  }
   }
}