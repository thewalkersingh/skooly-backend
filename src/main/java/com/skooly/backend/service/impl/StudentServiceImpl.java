package com.skooly.backend.service.impl;
import com.skooly.backend.dto.StudentRequestDTO;
import com.skooly.backend.dto.StudentResponseDTO;
import com.skooly.backend.entity.Student;
import com.skooly.backend.mapper.StudentMapper;
import com.skooly.backend.repository.StudentRepository;
import com.skooly.backend.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
   private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;
   
   @Override
   public StudentResponseDTO createStudent(StudentRequestDTO requestDTO) {
	  Student student = studentMapper.toStudent(requestDTO);
	  student = studentRepository.save(student);
	  return studentMapper.toStudentResponseDTO(student);
   }
   
   @Override
   public StudentResponseDTO getStudentById(Long id) {
	  Student student = studentRepository.findById(id)
										 .orElseThrow(() -> new EntityNotFoundException(
												 "Student not found with id: " + id));
	  return studentMapper.toStudentResponseDTO(student);
   }
   
   @Override
   public Page<StudentResponseDTO> getAllStudents(Pageable pageable) {
	  return studentRepository.findAll(pageable)
							  .map(studentMapper::toStudentResponseDTO);
   }
   
   @Override
   public Page<StudentResponseDTO> searchStudentsByName(String name, Pageable pageable) {
	  return studentRepository.findAllByFirstNameContainingIgnoreCase(name, pageable)
							  .map(studentMapper::toStudentResponseDTO);
   }
   
   @Override
   public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {
	  Student existing = studentRepository.findById(id)
										  .orElseThrow(() -> new EntityNotFoundException(
												  "Student not found with id: " + id));
	  
	  Student updated = studentMapper.toStudent(requestDTO);
	  updated.setId(existing.getId());  // keep same ID
	  studentRepository.save(updated);
	  
	  return studentMapper.toStudentResponseDTO(updated);
   }
   
   @Override
   public void deleteStudent(Long id) {
	  if(!studentRepository.existsById(id)){
		 throw new EntityNotFoundException("Student not found with id: " + id);
	  }
	  studentRepository.deleteById(id);
   }
}