package com.skooly.backend.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeeRecordDTO {
   @NotBlank(message = "Course code is required")
   private Long id;
   private Double totalFee;
   private Double dueAmount;
   private LocalDate dueDate;
   private boolean fullyPaid;
   private Long studentId;
   private Long courseId;
}