package com.skooly.backend.mapper;
import com.skooly.backend.dto.StudentDTO;
import com.skooly.backend.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
   @Mapping(source = "school.id", target = "schoolId")
   StudentDTO toDTO(Student student);
   
   // When converting from DTO to entity, we typically ignore the school since we will set that in service logic.
   @Mapping(target = "school", ignore = true)
   Student toEntity(StudentDTO studentDTO);
   
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "school", ignore = true)
   void updateStudentFromDTO(StudentDTO studentDTO, @MappingTarget Student student);
}