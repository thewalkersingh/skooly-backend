package com.skooly.backend.controller;
import com.skooly.backend.dto.CourseDTO;
import com.skooly.backend.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
//@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
   private final CourseService courseService;
   
   @Autowired
   public CourseController(CourseService courseService) {
	  this.courseService = courseService;
   }
   
   @GetMapping("/all")
   public List<CourseDTO> getAllCourses() {
	  return courseService.getAllCourses();
   }
   
   @GetMapping("/{id}")
   public CourseDTO getCourse(@PathVariable Long id) {
	  return courseService.getCourseById(id);
   }
   
   @PostMapping
   public CourseDTO createCourse(@Valid @RequestBody CourseDTO courseDTO) {
	  return courseService.saveCourse(courseDTO);
   }
   
   @PutMapping("/{id}")
   public CourseDTO updateCourse(@PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
	  return courseService.updateCourse(id, courseDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteCourse(@PathVariable Long id) {
	  courseService.deleteCourse(id);
   }
}