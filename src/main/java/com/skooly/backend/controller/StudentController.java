package com.skooly.backend.controller;
import com.skooly.backend.dto.StudentRequestDTO;
import com.skooly.backend.dto.StudentResponseDTO;
import com.skooly.backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
   private final StudentService studentService;
   
   @PostMapping
   public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO requestDTO) {
	  return studentService.createStudent(requestDTO);
   }
   
   @GetMapping("/{id}")
   public StudentResponseDTO getStudentById(@PathVariable Long id) {
	  return studentService.getStudentById(id);
   }
   
   @GetMapping
   public Page<StudentResponseDTO> getAllStudents(
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "10") int size) {
	  Pageable pageable = PageRequest.of(page, size);
	  return studentService.getAllStudents(pageable);
   }
   
   @GetMapping("/search")
   public Page<StudentResponseDTO> searchStudents(
		   @RequestParam String name,
		   @RequestParam(defaultValue = "0") int page,
		   @RequestParam(defaultValue = "10") int size) {
	  Pageable pageable = PageRequest.of(page, size);
	  return studentService.searchStudentsByName(name, pageable);
   }
   
   @PutMapping("/{id}")
   public StudentResponseDTO updateStudent(@PathVariable Long id,
		   @RequestBody StudentRequestDTO requestDTO) {
	  return studentService.updateStudent(id, requestDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteStudent(@PathVariable Long id) {
	  studentService.deleteStudent(id);
   }
}