package com.skooly.backend.controller;
import com.skooly.backend.dto.AssignmentDTO;
import com.skooly.backend.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
   private final AssignmentService assignmentService;
   
   @Autowired
   public AssignmentController(AssignmentService assignmentService) {
	  this.assignmentService = assignmentService;
   }
   
   @GetMapping
   public ResponseEntity<List<AssignmentDTO>> getAllAssignments() {
	  return ResponseEntity.ok(assignmentService.getAllAssignments());
   }
   
   @GetMapping("/course/{courseId}")
   public ResponseEntity<List<AssignmentDTO>> getAssignmentsByCourse(@PathVariable Long courseId) {
	  return ResponseEntity.ok(assignmentService.getAssignmentsByCourse(courseId));
   }
   
   @PostMapping
   public ResponseEntity<AssignmentDTO> createAssignment(@RequestBody AssignmentDTO assignmentDTO) {
	  return ResponseEntity.ok(assignmentService.createAssignment(assignmentDTO));
   }
}