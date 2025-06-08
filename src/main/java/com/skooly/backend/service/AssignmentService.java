package com.skooly.backend.service;
import com.skooly.backend.dto.AssignmentDTO;
import com.skooly.backend.entity.Assignment;
import com.skooly.backend.entity.Course;
import com.skooly.backend.exception.CourseNotFoundException;
import com.skooly.backend.mapper.AssignmentMapper;
import com.skooly.backend.repository.AssignmentRepository;
import com.skooly.backend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentService {
   private final AssignmentRepository assignmentRepository;
   private final AssignmentMapper assignmentMapper;
   private final CourseRepository courseRepository;
   
   @Autowired
   public AssignmentService(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper,
		   CourseRepository courseRepository) {
	  this.assignmentRepository = assignmentRepository;
	  this.assignmentMapper = assignmentMapper;
	  this.courseRepository = courseRepository;
   }
   
   public List<AssignmentDTO> getAllAssignments() {
	  return assignmentRepository.findAll()
								 .stream()
								 .map(assignmentMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public List<AssignmentDTO> getAssignmentsByCourse(Long courseId) {
	  return assignmentRepository.findByCourseId(courseId)
								 .stream()
								 .map(assignmentMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public AssignmentDTO createAssignment(AssignmentDTO assignmentDTO) {
	  Course course = courseRepository.findById(assignmentDTO.getCourseId())
									  .orElseThrow(() -> new CourseNotFoundException("Course not found"));
	  Assignment assignment = assignmentMapper.toEntity(assignmentDTO);
	  assignment.setCourse(course);
	  return assignmentMapper.toDTO(assignmentRepository.save(assignment));
   }
}