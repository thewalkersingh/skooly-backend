package com.skooly.backend.service;
import com.skooly.backend.dto.SchoolDTO;
import com.skooly.backend.entity.School;
import com.skooly.backend.exception.SchoolNotFoundException;
import com.skooly.backend.mapper.SchoolMapper;
import com.skooly.backend.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {
   private final SchoolRepository schoolRepository;
   private final SchoolMapper schoolMapper;
   
   @Autowired public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
	  this.schoolRepository = schoolRepository;
	  this.schoolMapper = schoolMapper;
   }
   
   public List<SchoolDTO> getAllSchools() {
	  System.out.println("Getting all schools");
	  return schoolRepository.findAll().stream().map(schoolMapper::toDto).collect(Collectors.toList());
   }
   
   public SchoolDTO getSchoolById(Long id) {
	  School school = schoolRepository.findById(id)
									  .orElseThrow(() -> new SchoolNotFoundException("School not found"));
	  school.getTeachers().size();
	  System.out.println("Getting school with id " + id);
	  return schoolMapper.toDto(school);
   }
   
   public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
	  School school = schoolMapper.toEntity(schoolDTO);
	  School savedSchool = schoolRepository.save(school);
	  System.out.println("School Saved Successfully");
	  return schoolMapper.toDto(savedSchool);
   }
   
   public String saveSchools(List<SchoolDTO> schoolDTOs) {
	  // 1. Convert all DTOs to entities
	  List<School> entities = schoolDTOs.stream().map(schoolMapper::toEntity).collect(Collectors.toList());
	  // 2. Save them in one go
	  schoolRepository.saveAll(entities);
	  // 3. Convert back to DTOs
	  return "All Schools saved successfully!";
   }
   
   public SchoolDTO updateSchool(Long id, SchoolDTO schoolDTO) {
	  School existingSchool = schoolRepository.findById(id)
											  .orElseThrow(() -> new SchoolNotFoundException("School not found"));
	  if(existingSchool == null){
		 throw new SchoolNotFoundException("School not found with id " + id);
	  }
	  schoolMapper.updateSchoolFromDTO(schoolDTO, existingSchool);
	  School updatedSchool = schoolRepository.save(existingSchool);
	  System.out.println("School updated successfully");
	  return schoolMapper.toDto(updatedSchool);
   }
   
   public void deleteSchool(Long id) {
	  if(!schoolRepository.existsById(id)){
		 throw new SchoolNotFoundException("School not found with id " + id);
	  }
	  System.out.println("School deleted successfully");
	  schoolRepository.deleteById(id);
   }
}