package com.skooly.backend.service;
import com.skooly.backend.dto.StudentDTO;
import com.skooly.backend.dto.SubmissionDTO;
import com.skooly.backend.entity.School;
import com.skooly.backend.entity.Student;
import com.skooly.backend.exception.SchoolNotFoundException;
import com.skooly.backend.exception.StudentNotFoundException;
import com.skooly.backend.mapper.StudentMapper;
import com.skooly.backend.mapper.SubmissionMapper;
import com.skooly.backend.repository.SchoolRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
   private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;
   private final SchoolRepository schoolRepository;
   private final SubmissionMapper submissionMapper;
   
   @Autowired
   public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
		   SchoolRepository schoolRepository, SubmissionMapper submissionMapper) {
	  this.studentRepository = studentRepository;
	  this.studentMapper = studentMapper;
	  this.schoolRepository = schoolRepository;
	  this.submissionMapper = submissionMapper;
   }
   
   public List<StudentDTO> getAllStudents() {
	  return studentRepository.findAll().stream()
							  .map(studentMapper::toDTO)
							  .collect(Collectors.toList());
   }
   
   public StudentDTO saveStudent(StudentDTO studentDTO) {
	  Student student = studentMapper.toEntity(studentDTO);
	  if(studentDTO.getSchoolId() != null){
		 School school = schoolRepository.findById(studentDTO.getSchoolId())
										 .orElseThrow(() -> new SchoolNotFoundException("School not found"));
		 student.setSchool(school);
	  }
	  Student savedStudent = studentRepository.save(student);
	  System.out.println("Student saved successfully");
	  return studentMapper.toDTO(savedStudent);
   }
   
   public String saveAll(List<StudentDTO> studentDTOS) {
	  List<Student> entities = studentDTOS.stream().map(studentDTO -> {
		 Student student = studentMapper.toEntity(studentDTO);
		 if(studentDTO.getSchoolId() != null){
			School school = schoolRepository.findById(studentDTO.getSchoolId())
											.orElseThrow(() -> new SchoolNotFoundException("School not found"));
			student.setSchool(school);
		 }
		 return student;
	  }).collect(Collectors.toList());
	  studentRepository.saveAll(entities);
	  return "All students saved successfully!";
   }
   
   public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
	  Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found"));
	  studentMapper.updateStudentFromDTO(studentDTO, student);
	  Student updatedStudent = studentRepository.save(student);
	  System.out.println("Student updated successfully");
	  return studentMapper.toDTO(updatedStudent);
   }
   
   public List<SubmissionDTO> getStudentSubmissions(Long studentId) {
	  Student student = studentRepository.findById(studentId)
										 .orElseThrow(() -> new StudentNotFoundException("Student not found"));
	  
	  return student.getSubmissions()
					.stream()
					.map(submissionMapper::toDTO)
					.collect(Collectors.toList());
   }
   
   public void deleteStudent(Long id) {
	  studentRepository.deleteById(id);
   }
}