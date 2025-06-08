package com.skooly.backend.mapper;
import com.skooly.backend.dto.TeacherDTO;
import com.skooly.backend.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {GradeMapper.class})
public interface TeacherMapper {
   @Mapping(source = "school.id", target = "schoolId")
   @Mapping(target = "gradedAssignmentIds", expression = "java(getGradedAssignmentIds(teacher))")
   TeacherDTO toDTO(Teacher teacher);
   
   // When converting from DTO to entity we ignore the school field;
   // we'll set it in service logic.
   @Mapping(target = "school", ignore = true)
   @Mapping(target = "gradedAssignments", ignore = true)
   Teacher toEntity(TeacherDTO teacherDTO);
   
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "school", ignore = true)
   void updateTeacherFromDTO(TeacherDTO teacherDTO, @MappingTarget Teacher teacher);
   
   default List<Long> getGradedAssignmentIds(Teacher teacher) {
	  if(teacher == null || teacher.getGradedAssignments() == null){
		 return null;
	  }
	  return teacher.getGradedAssignments()
					.stream()
					.map(grade -> grade.getSubmission().getId())
					.collect(Collectors.toList());
   }
}