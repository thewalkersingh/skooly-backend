package com.skooly.backend.mapper;
import com.skooly.backend.dto.StudentRequestDTO;
import com.skooly.backend.dto.StudentResponseDTO;
import com.skooly.backend.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StudentMapper {
   StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
   
   Student toStudent(StudentRequestDTO dto);
   
   StudentResponseDTO toStudentResponseDTO(Student student);
}