package com.skooly.backend.service;
import com.skooly.backend.dto.SchoolStaffRequestDTO;
import com.skooly.backend.dto.SchoolStaffResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SchoolStaffService {
   SchoolStaffResponseDTO createStaff(SchoolStaffRequestDTO requestDTO);
   
   SchoolStaffResponseDTO getStaffById(Long id);
   
   Page<SchoolStaffResponseDTO> getAllStaff(Pageable pageable);
   
   SchoolStaffResponseDTO updateStaff(Long id, SchoolStaffRequestDTO requestDTO);
   
   void deleteStaff(Long id);
}