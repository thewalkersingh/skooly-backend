package com.skooly.backend.service;
import com.skooly.backend.dto.GradeDTO;
import com.skooly.backend.dto.TeacherDTO;
import com.skooly.backend.entity.School;
import com.skooly.backend.entity.Teacher;
import com.skooly.backend.exception.SchoolNotFoundException;
import com.skooly.backend.exception.TeacherNotFoundException;
import com.skooly.backend.mapper.GradeMapper;
import com.skooly.backend.mapper.TeacherMapper;
import com.skooly.backend.repository.SchoolRepository;
import com.skooly.backend.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
   private final TeacherRepository teacherRepository;
   private final TeacherMapper teacherMapper;
   private final SchoolRepository schoolRepository;
   private final GradeMapper gradeMapper;
   
   @Autowired
   public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper,
		   SchoolRepository schoolRepository, GradeMapper gradeMapper) {
	  this.teacherRepository = teacherRepository;
	  this.teacherMapper = teacherMapper;
	  this.schoolRepository = schoolRepository;
	  this.gradeMapper = gradeMapper;
   }
   
   public List<TeacherDTO> getAllTeachers() {
	  return teacherRepository.findAll()
							  .stream()
							  .map(teacherMapper::toDTO)
							  .collect(Collectors.toList());
   }
   
   public TeacherDTO saveTeacher(TeacherDTO teacherDTO) {
	  Teacher teacher = teacherMapper.toEntity(teacherDTO);
	  if(teacherDTO.getSchoolId() != null){
		 School school = schoolRepository.findById(teacherDTO.getSchoolId())
										 .orElseThrow(() -> new SchoolNotFoundException("School not found"));
		 teacher.setSchool(school);
	  }
	  Teacher savedTeacher = teacherRepository.save(teacher);
	  return teacherMapper.toDTO(savedTeacher);
   }
   
   public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
	  Teacher teacher = teacherRepository.findById(id)
										 .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
	  teacherMapper.updateTeacherFromDTO(teacherDTO, teacher);
	  Teacher updatedTeacher = teacherRepository.save(teacher);
	  return teacherMapper.toDTO(updatedTeacher);
   }
   
   public void deleteTeacher(Long id) {
	  teacherRepository.deleteById(id);
   }
   
   public List<GradeDTO> getTeacherGradedAssignments(Long teacherId) {
	  Teacher teacher = teacherRepository.findById(teacherId)
										 .orElseThrow(() -> new TeacherNotFoundException("Teacher not found"));
	  
	  return teacher.getGradedAssignments()
					.stream()
					.map(gradeMapper::toDTO)
					.collect(Collectors.toList());
   }
}