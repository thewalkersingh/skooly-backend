package com.skooly.backend.mapper;
import com.skooly.backend.dto.GradeDTO;
import com.skooly.backend.entity.Grade;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GradeMapper {
   @Mapping(source = "submission.id", target = "submissionId")
   @Mapping(source = "gradedBy.id", target = "teacherId")
   GradeDTO toDTO(Grade grade);
   
   @Mapping(target = "submission", ignore = true)
   @Mapping(target = "gradedBy", ignore = true)
   Grade toEntity(GradeDTO gradeDTO);
}