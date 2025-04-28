package com.skooly.backend.service.impl;

import com.skooly.backend.dto.TeacherRequestDTO;
import com.skooly.backend.dto.TeacherResponseDTO;
import com.skooly.backend.entity.Teacher;
import com.skooly.backend.mapper.TeacherMapper;
import com.skooly.backend.repository.TeacherRepository;
import com.skooly.backend.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
   
   private final TeacherRepository teacherRepository;
   private final TeacherMapper teacherMapper;
   
   @Override
   public TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO) {
	  Teacher teacher = teacherMapper.toTeacher(requestDTO);
	  teacher = teacherRepository.save(teacher);
	  return teacherMapper.toTeacherResponseDTO(teacher);
   }
   
   @Override
   public TeacherResponseDTO getTeacherById(Long id) {
	  Teacher teacher = teacherRepository.findById(id)
										 .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
	  return teacherMapper.toTeacherResponseDTO(teacher);
   }
   
   @Override
   public Page<TeacherResponseDTO> getAllTeachers(Pageable pageable) {
	  return teacherRepository.findAll(pageable)
							  .map(teacherMapper::toTeacherResponseDTO);
   }
   
   @Override
   public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO requestDTO) {
	  Teacher existing = teacherRepository.findById(id)
										  .orElseThrow(() -> new EntityNotFoundException("Teacher not found with id: " + id));
	  Teacher updated = teacherMapper.toTeacher(requestDTO);
	  updated.setId(existing.getId());
	  teacherRepository.save(updated);
	  return teacherMapper.toTeacherResponseDTO(updated);
   }
   
   @Override
   public void deleteTeacher(Long id) {
	  if (!teacherRepository.existsById(id)) {
		 throw new EntityNotFoundException("Teacher not found with id: " + id);
	  }
	  teacherRepository.deleteById(id);
   }
}