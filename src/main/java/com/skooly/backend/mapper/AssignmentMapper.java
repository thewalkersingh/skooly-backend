package com.skooly.backend.mapper;
import com.skooly.backend.dto.AssignmentDTO;
import com.skooly.backend.entity.Assignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AssignmentMapper {
   @Mapping(source = "course.id", target = "courseId")
   AssignmentDTO toDTO(Assignment assignment);
   
   @Mapping(target = "course", ignore = true) // Course will be set in service layer
   Assignment toEntity(AssignmentDTO assignmentDTO);
}