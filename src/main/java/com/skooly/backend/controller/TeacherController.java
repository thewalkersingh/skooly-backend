package com.skooly.backend.controller;
import com.skooly.backend.dto.TeacherDTO;
import com.skooly.backend.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
//@CrossOrigin(origins = "http://localhost:5173")
@Validated
public class TeacherController {
   private final TeacherService teacherService;
   
   @Autowired
   public TeacherController(TeacherService teacherService) {
	  this.teacherService = teacherService;
   }
   
   @GetMapping("/all")
   public List<TeacherDTO> getAllTeachers() {
	  return teacherService.getAllTeachers();
   }
   
   @PostMapping
   public TeacherDTO createTeacher(@Valid @RequestBody TeacherDTO teacherDTO) {
	  return teacherService.saveTeacher(teacherDTO);
   }
   
   @PutMapping("/{id}")
   public TeacherDTO updateTeacher(@PathVariable Long id, @Valid @RequestBody TeacherDTO teacherDTO) {
	  return teacherService.updateTeacher(id, teacherDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteTeacher(@PathVariable Long id) {
	  teacherService.deleteTeacher(id);
   }
}