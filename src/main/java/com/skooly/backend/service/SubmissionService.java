package com.skooly.backend.service;

import com.skooly.backend.dto.SubmissionDTO;
import com.skooly.backend.entity.Submission;
import com.skooly.backend.entity.Assignment;
import com.skooly.backend.entity.Student;
import com.skooly.backend.exception.AssignmentNotFoundException;
import com.skooly.backend.exception.StudentNotFoundException;
import com.skooly.backend.mapper.SubmissionMapper;
import com.skooly.backend.repository.SubmissionRepository;
import com.skooly.backend.repository.AssignmentRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionService {
   private final SubmissionRepository submissionRepository;
   private final SubmissionMapper submissionMapper;
   private final AssignmentRepository assignmentRepository;
   private final StudentRepository studentRepository;
   
   @Autowired
   public SubmissionService(SubmissionRepository submissionRepository, SubmissionMapper submissionMapper,
		   AssignmentRepository assignmentRepository, StudentRepository studentRepository) {
	  this.submissionRepository = submissionRepository;
	  this.submissionMapper = submissionMapper;
	  this.assignmentRepository = assignmentRepository;
	  this.studentRepository = studentRepository;
   }
   
   public List<SubmissionDTO> getSubmissionsByAssignment(Long assignmentId) {
	  return submissionRepository.findByAssignmentId(assignmentId)
								 .stream()
								 .map(submissionMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public List<SubmissionDTO> getSubmissionsByStudent(Long studentId) {
	  return submissionRepository.findByStudentId(studentId)
								 .stream()
								 .map(submissionMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public SubmissionDTO submitAssignment(SubmissionDTO submissionDTO) {
	  Assignment assignment = assignmentRepository.findById(submissionDTO.getAssignmentId())
												  .orElseThrow(() -> new AssignmentNotFoundException("Assignment not found"));
	  Student student = studentRepository.findById(submissionDTO.getStudentId())
										 .orElseThrow(() -> new StudentNotFoundException("Student not found"));
	  
	  Submission submission = submissionMapper.toEntity(submissionDTO);
	  submission.setAssignment(assignment);
	  submission.setStudent(student);
	  submission.setSubmittedAt(LocalDateTime.now());
	  return submissionMapper.toDTO(submissionRepository.save(submission));
   }
}