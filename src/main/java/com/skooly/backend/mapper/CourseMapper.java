package com.skooly.backend.mapper;
import com.skooly.backend.dto.CourseDTO;
import com.skooly.backend.entity.Course;
import com.skooly.backend.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {
   @Mapping(source = "teacher.id", target = "teacherId")
   // Custom mapping: convert List<Student> to List<Long> of student IDs.
   @Mapping(target = "studentIds", expression = "java(getStudentIds(course.getStudents()))")
   CourseDTO toDTO(Course course);
   
   @Mapping(target = "teacher", ignore = true)
   @Mapping(target = "students", ignore = true) // We'll handle this in the service layer.
   Course toEntity(CourseDTO courseDTO);
   
   default List<Long> getStudentIds(List<Student> students) {
	  if(students == null)
		 return null;
	  return students.stream().map(Student::getId).collect(Collectors.toList());
   }
   
   // For updates (if needed)
   @Mapping(target = "teacher", ignore = true)
   @Mapping(target = "students", ignore = true)
   void updateCourseFromDTO(CourseDTO courseDTO, @MappingTarget Course course);
}