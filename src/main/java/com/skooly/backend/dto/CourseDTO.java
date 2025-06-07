package com.skooly.backend.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
   private Long id;
   
   @NotBlank(message = "Course code is required")
   private String courseCode;
   
   @NotBlank(message = "Course name is required")
   private String name;
   private String description;
   private String schedule;
   
   @NotNull(message = "Teacher ID is required")
   private Long teacherId;
   // List of student IDs enrolled in the course.
   private List<Long> studentIds;
}