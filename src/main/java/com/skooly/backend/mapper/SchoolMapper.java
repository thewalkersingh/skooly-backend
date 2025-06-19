package com.skooly.backend.mapper;
import com.skooly.backend.dto.SchoolDTO;
import com.skooly.backend.entity.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SchoolMapper {
   // Basic mapping method
   @Mapping(target = "teachers", source = "teachers")
   SchoolDTO toDto(School school);
   
   // When converting from DTO to entity, provide default values if needed
   @Mapping(target = "creationDate", expression = "java(java.time.LocalDate.now())")
   @Mapping(target = "teachers", ignore = true)
   School toEntity(SchoolDTO schoolDTO);
   
   // Update an existing entity with DTO data:
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "creationDate", ignore = true)
   void updateSchoolFromDTO(SchoolDTO schoolDTO, @MappingTarget School school);
}