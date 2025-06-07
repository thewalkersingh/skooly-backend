package com.skooly.backend.controller;
import com.skooly.backend.dto.StudentDTO;
import com.skooly.backend.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
//@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class StudentController {
   private final StudentService studentService;
   
   @Autowired
   public StudentController(StudentService studentService) {
	  this.studentService = studentService;
   }
   
   @GetMapping("/all")
   public List<StudentDTO> getAllStudents() {
	  return studentService.getAllStudents();
   }
   
   @PostMapping
   public StudentDTO createStudent(@Valid @RequestBody StudentDTO studentDTO) {
	  return studentService.saveStudent(studentDTO);
   }
   
   // Bulk-create schools expects a JSON array of StudentDTO objects.
   @PostMapping("/batch")
   public String createStudents(@RequestBody List<@Valid StudentDTO> studentDTOS) {
	  return studentService.saveAll(studentDTOS);
   }
   
   @PutMapping("/{id}")
   public StudentDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
	  return studentService.updateStudent(id, studentDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteStudent(@PathVariable Long id) {
	  studentService.deleteStudent(id);
   }
}