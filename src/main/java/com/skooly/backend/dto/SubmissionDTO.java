package com.skooly.backend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionDTO {
   private Long id;
   
   @NotNull(message = "Assignment ID is required")
   private Long assignmentId;
   
   @NotNull(message = "Student ID is required")
   private Long studentId;
   private String fileUrl;
   
   @NotNull(message = "Submission date is required")
   private LocalDateTime submittedAt;
   
   @NotNull(message = "Status is required")
   private String status;
}