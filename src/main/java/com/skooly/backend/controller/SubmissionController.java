package com.skooly.backend.controller;
import com.skooly.backend.dto.SubmissionDTO;
import com.skooly.backend.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
   private final SubmissionService submissionService;
   
   @Autowired
   public SubmissionController(SubmissionService submissionService) {
	  this.submissionService = submissionService;
   }
   
   @GetMapping("/assignment/{assignmentId}")
   public ResponseEntity<List<SubmissionDTO>> getSubmissionsByAssignment(@PathVariable Long assignmentId) {
	  return ResponseEntity.ok(submissionService.getSubmissionsByAssignment(assignmentId));
   }
   
   @GetMapping("/student/{studentId}")
   public ResponseEntity<List<SubmissionDTO>> getSubmissionsByStudent(@PathVariable Long studentId) {
	  return ResponseEntity.ok(submissionService.getSubmissionsByStudent(studentId));
   }
   
   @PostMapping
   public ResponseEntity<SubmissionDTO> submitAssignment(@RequestBody SubmissionDTO submissionDTO) {
	  return ResponseEntity.ok(submissionService.submitAssignment(submissionDTO));
   }
}