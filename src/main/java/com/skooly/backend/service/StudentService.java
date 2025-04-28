package com.skooly.backend.service;
import com.skooly.backend.dto.StudentRequestDTO;
import com.skooly.backend.dto.StudentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {
   StudentResponseDTO createStudent(StudentRequestDTO requestDTO);
   
   StudentResponseDTO getStudentById(Long id);
   
   Page<StudentResponseDTO> getAllStudents(Pageable pageable);
   
   Page<StudentResponseDTO> searchStudentsByName(String name, Pageable pageable);
   
   StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO);
   
   void deleteStudent(Long id);
}