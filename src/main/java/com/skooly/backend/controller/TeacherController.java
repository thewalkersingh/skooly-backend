package com.skooly.backend.controller;
import com.skooly.backend.dto.TeacherRequestDTO;
import com.skooly.backend.dto.TeacherResponseDTO;
import com.skooly.backend.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {
   private final TeacherService teacherService;
   
   @PostMapping
   public TeacherResponseDTO createTeacher(@RequestBody TeacherRequestDTO requestDTO) {
	  return teacherService.createTeacher(requestDTO);
   }
   
   @GetMapping("/{id}")
   public TeacherResponseDTO getTeacherById(@PathVariable Long id) {
	  return teacherService.getTeacherById(id);
   }
   
   @GetMapping
   public Page<TeacherResponseDTO> getAllTeachers(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "10") int size) {
	  Pageable pageable = PageRequest.of(page, size);
	  return teacherService.getAllTeachers(pageable);
   }
   
   @PutMapping("/{id}")
   public TeacherResponseDTO updateTeacher(@PathVariable Long id,
		   @RequestBody TeacherRequestDTO requestDTO) {
	  return teacherService.updateTeacher(id, requestDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteTeacher(@PathVariable Long id) {
	  teacherService.deleteTeacher(id);
   }
}