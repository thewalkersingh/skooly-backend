package com.skooly.backend.mapper;
import com.skooly.backend.dto.TeacherDTO;
import com.skooly.backend.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
   @Mapping(source = "school.id", target = "schoolId")
   TeacherDTO toDTO(Teacher teacher);
   
   // When converting from DTO to entity we ignore the school field;
   // we'll set it in service logic.
   @Mapping(target = "school", ignore = true)
   Teacher toEntity(TeacherDTO teacherDTO);
   
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "school", ignore = true)
   void updateTeacherFromDTO(TeacherDTO teacherDTO, @MappingTarget Teacher teacher);
}