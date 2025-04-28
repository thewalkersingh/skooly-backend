package com.skooly.backend.mapper;
import com.skooly.backend.dto.TeacherRequestDTO;
import com.skooly.backend.dto.TeacherResponseDTO;
import com.skooly.backend.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
   TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
   
   Teacher toTeacher(TeacherRequestDTO dto);
   
   TeacherResponseDTO toTeacherResponseDTO(Teacher teacher);
}