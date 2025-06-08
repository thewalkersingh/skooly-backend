package com.skooly.backend.controller;
import com.skooly.backend.dto.GradeDTO;
import com.skooly.backend.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {
   private final GradeService gradeService;
   
   @Autowired
   public GradeController(GradeService gradeService) {
	  this.gradeService = gradeService;
   }
   
   @GetMapping("/submission/{submissionId}")
   public ResponseEntity<List<GradeDTO>> getGradesBySubmission(@PathVariable Long submissionId) {
	  return ResponseEntity.ok(gradeService.getGradesBySubmission(submissionId));
   }
   
   @PostMapping
   public ResponseEntity<GradeDTO> gradeAssignment(@RequestBody GradeDTO gradeDTO) {
	  return ResponseEntity.ok(gradeService.gradeAssignment(gradeDTO));
   }
}