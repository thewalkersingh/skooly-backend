package com.skooly.backend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentDTO {
   private Long id;
   
   @NotNull(message = "Title is required")
   private String title;
   private String description;
   
   @NotNull(message = "Due date is required")
   private LocalDate dueDate;
   
   @NotNull(message = "Course ID is required")
   private Long courseId;
}