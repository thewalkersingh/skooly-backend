package com.skooly.backend.dto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceDTO {
   private Long id;
   
   @NotNull(message = "Student ID is required")
   private Long studentId;
   
   @NotNull(message = "Date is required")
   private LocalDate date;
   
   @NotNull(message = "Attendance status is required")
   private String status;
   private String remarks;
}