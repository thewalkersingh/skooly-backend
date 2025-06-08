package com.skooly.backend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {
   private Long id;
   
   @NotNull(message = "Submission ID is required")
   private Long submissionId;
   
   @NotNull(message = "Teacher ID is required")
   private Long teacherId;
   
   @NotNull(message = "Score is required")
   private Integer score;
   private String feedback;
}