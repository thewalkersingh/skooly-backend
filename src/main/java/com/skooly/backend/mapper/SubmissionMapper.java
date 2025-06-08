package com.skooly.backend.mapper;
import com.skooly.backend.dto.SubmissionDTO;
import com.skooly.backend.entity.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubmissionMapper {
   @Mapping(source = "assignment.id", target = "assignmentId")
   @Mapping(source = "student.id", target = "studentId")
   @Mapping(source = "status", target = "status")
   SubmissionDTO toDTO(Submission submission);
   
   @Mapping(target = "assignment", ignore = true)
   @Mapping(target = "student", ignore = true)
   Submission toEntity(SubmissionDTO submissionDTO);
}