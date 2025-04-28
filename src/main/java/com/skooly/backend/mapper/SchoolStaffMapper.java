package com.skooly.backend.mapper;
import com.skooly.backend.dto.SchoolStaffRequestDTO;
import com.skooly.backend.dto.SchoolStaffResponseDTO;
import com.skooly.backend.entity.SchoolStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SchoolStaffMapper {
   SchoolStaffMapper INSTANCE = Mappers.getMapper(SchoolStaffMapper.class);
   
   SchoolStaff toSchoolStaff(SchoolStaffRequestDTO dto);
   
   SchoolStaffResponseDTO toSchoolStaffResponseDTO(SchoolStaff staff);
}