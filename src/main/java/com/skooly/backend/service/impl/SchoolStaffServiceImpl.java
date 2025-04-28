package com.skooly.backend.service.impl;

import com.skooly.backend.dto.SchoolStaffRequestDTO;
import com.skooly.backend.dto.SchoolStaffResponseDTO;
import com.skooly.backend.entity.SchoolStaff;
import com.skooly.backend.mapper.SchoolStaffMapper;
import com.skooly.backend.repository.SchoolStaffRepository;
import com.skooly.backend.service.SchoolStaffService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolStaffServiceImpl implements SchoolStaffService {
   
   private final SchoolStaffRepository schoolStaffRepository;
   private final SchoolStaffMapper schoolStaffMapper;
   
   @Override
   public SchoolStaffResponseDTO createStaff(SchoolStaffRequestDTO requestDTO) {
	  SchoolStaff staff = schoolStaffMapper.toSchoolStaff(requestDTO);
	  staff = schoolStaffRepository.save(staff);
	  return schoolStaffMapper.toSchoolStaffResponseDTO(staff);
   }
   
   @Override
   public SchoolStaffResponseDTO getStaffById(Long id) {
	  SchoolStaff staff = schoolStaffRepository.findById(id)
											   .orElseThrow(() -> new EntityNotFoundException("School Staff not found with id: " + id));
	  return schoolStaffMapper.toSchoolStaffResponseDTO(staff);
   }
   
   @Override
   public Page<SchoolStaffResponseDTO> getAllStaff(Pageable pageable) {
	  return schoolStaffRepository.findAll(pageable)
								  .map(schoolStaffMapper::toSchoolStaffResponseDTO);
   }
   
   @Override
   public SchoolStaffResponseDTO updateStaff(Long id, SchoolStaffRequestDTO requestDTO) {
	  SchoolStaff existing = schoolStaffRepository.findById(id)
												  .orElseThrow(() -> new EntityNotFoundException("School Staff not found with id: " + id));
	  SchoolStaff updated = schoolStaffMapper.toSchoolStaff(requestDTO);
	  updated.setId(existing.getId());
	  schoolStaffRepository.save(updated);
	  return schoolStaffMapper.toSchoolStaffResponseDTO(updated);
   }
   
   @Override
   public void deleteStaff(Long id) {
	  if (!schoolStaffRepository.existsById(id)) {
		 throw new EntityNotFoundException("School Staff not found with id: " + id);
	  }
	  schoolStaffRepository.deleteById(id);
   }
}