package com.skooly.backend.service;
import com.skooly.backend.dto.TeacherRequestDTO;
import com.skooly.backend.dto.TeacherResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
   TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO);
   
   TeacherResponseDTO getTeacherById(Long id);
   
   Page<TeacherResponseDTO> getAllTeachers(Pageable pageable);
   
   TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO requestDTO);
   
   void deleteTeacher(Long id);
}