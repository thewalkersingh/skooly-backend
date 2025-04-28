package com.skooly.backend.controller;
import com.skooly.backend.dto.SchoolStaffRequestDTO;
import com.skooly.backend.dto.SchoolStaffResponseDTO;
import com.skooly.backend.service.SchoolStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@RequiredArgsConstructor
public class SchoolStaffController {
   private final SchoolStaffService schoolStaffService;
   
   @PostMapping
   public SchoolStaffResponseDTO createStaff(@RequestBody SchoolStaffRequestDTO requestDTO) {
	  return schoolStaffService.createStaff(requestDTO);
   }
   
   @GetMapping("/{id}")
   public SchoolStaffResponseDTO getStaffById(@PathVariable Long id) {
	  return schoolStaffService.getStaffById(id);
   }
   
   @GetMapping
   public Page<SchoolStaffResponseDTO> getAllStaff(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "10") int size) {
	  Pageable pageable = PageRequest.of(page, size);
	  return schoolStaffService.getAllStaff(pageable);
   }
   
   @PutMapping("/{id}")
   public SchoolStaffResponseDTO updateStaff(@PathVariable Long id,
		   @RequestBody SchoolStaffRequestDTO requestDTO) {
	  return schoolStaffService.updateStaff(id, requestDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteStaff(@PathVariable Long id) {
	  schoolStaffService.deleteStaff(id);
   }
}