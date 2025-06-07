package com.skooly.backend.controller;
import com.skooly.backend.dto.SchoolDTO;
import com.skooly.backend.service.SchoolService;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/schools")
public class SchoolController {
   private final SchoolService schoolService;
   
   public SchoolController(SchoolService schoolService) {
	  this.schoolService = schoolService;
   }
   
   @GetMapping("/all")
   public List<SchoolDTO> getAllSchools() {
	  return schoolService.getAllSchools();
   }
   
   @GetMapping("/{id}")
   public SchoolDTO getSchoolById(@PathVariable("id") Long id) {
	  return schoolService.getSchoolById(id);
   }
   
   @PostMapping
   public SchoolDTO createSchool(@Valid @RequestBody SchoolDTO schoolDTO) {
	  return schoolService.saveSchool(schoolDTO);
   }
   
   // Bulk-create schools expects a JSON array of SchoolDTO objects.
   @PostMapping("/batch")
   public String createSchools(@RequestBody List<@Valid SchoolDTO> schoolDTOs) {
	  return schoolService.saveSchools(schoolDTOs);
   }
   
   @PutMapping("/{id}")
   public SchoolDTO updateSchool(@PathVariable("id") Long id, @Valid @RequestBody SchoolDTO schoolDTO) {
	  return schoolService.updateSchool(id, schoolDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteSchool(@PathVariable("id") Long id) {
	  schoolService.deleteSchool(id);
   }
}