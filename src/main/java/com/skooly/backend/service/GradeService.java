package com.skooly.backend.service;

import com.skooly.backend.dto.GradeDTO;
import com.skooly.backend.entity.Grade;
import com.skooly.backend.entity.Submission;
import com.skooly.backend.entity.Teacher;
import com.skooly.backend.exception.SubmissionNotFoundException;
import com.skooly.backend.exception.TeacherNotFoundException;
import com.skooly.backend.mapper.GradeMapper;
import com.skooly.backend.repository.GradeRepository;
import com.skooly.backend.repository.SubmissionRepository;
import com.skooly.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {
   private final GradeRepository gradeRepository;
   private final GradeMapper gradeMapper;
   private final SubmissionRepository submissionRepository;
   private final TeacherRepository teacherRepository;
   
   @Autowired
   public GradeService(GradeRepository gradeRepository, GradeMapper gradeMapper,
		   SubmissionRepository submissionRepository, TeacherRepository teacherRepository) {
	  this.gradeRepository = gradeRepository;
	  this.gradeMapper = gradeMapper;
	  this.submissionRepository = submissionRepository;
	  this.teacherRepository = teacherRepository;
   }
   
   public List<GradeDTO> getGradesBySubmission(Long submissionId) {
	  return gradeRepository.findBySubmissionId(submissionId)
							.stream()
							.map(gradeMapper::toDTO)
							.collect(Collectors.toList());
   }
   
   public GradeDTO gradeAssignment(GradeDTO gradeDTO) {
	  Submission submission = submissionRepository.findById(gradeDTO.getSubmissionId())
												  .orElseThrow(() -> new SubmissionNotFoundException("Submission not found"));
	  Teacher teacher = teacherRepository.findById(gradeDTO.getTeacherId())
										 .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
	  
	  Grade grade = gradeMapper.toEntity(gradeDTO);
	  grade.setSubmission(submission);
	  grade.setGradedBy(teacher);
	  return gradeMapper.toDTO(gradeRepository.save(grade));
   }
}