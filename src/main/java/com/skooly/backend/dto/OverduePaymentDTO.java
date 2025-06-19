package com.skooly.backend.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverduePaymentDTO {
   private Long id;
   private Double overdueAmount;
   private LocalDate overdueDate;
   private boolean resolved;
   private Long studentId;
}