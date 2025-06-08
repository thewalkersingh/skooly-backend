package com.skooly.backend.mapper;
import com.skooly.backend.dto.StudentDTO;
import com.skooly.backend.entity.Student;
import com.skooly.backend.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {SubmissionMapper.class})
public interface StudentMapper {
   @Mapping(source = "school.id", target = "schoolId")
   @Mapping(target = "submissionIds", expression = "java(getSubmissionIds(student))")
   StudentDTO toDTO(Student student);
   
   // When converting from DTO to entity, we typically ignore the school since we will set that in service logic.
   @Mapping(target = "school", ignore = true)
   @Mapping(target = "submissions", ignore = true)
   Student toEntity(StudentDTO studentDTO);
   
   @Mapping(target = "id", ignore = true)
   @Mapping(target = "school", ignore = true)
   void updateStudentFromDTO(StudentDTO studentDTO, @MappingTarget Student student);
   
   default List<Long> getSubmissionIds(Student student) {
	  if(student == null || student.getSubmissions() == null){
		 return null;
	  }
	  return student.getSubmissions()
					.stream()
					.map(Submission::getId)
					.collect(Collectors.toList());
   }
}