package com.skooly.backend.dto;
import com.skooly.backend.enums.PaymentMethod;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
   private Long id;
   
   @NotNull(message = "Amount Paid is required")
   private Double amountPaid;
   
   @NotNull
   private LocalDate paymentDate;
   
   @NotNull
   private PaymentMethod paymentMethod;
   
   @NotNull
   private Long studentId;
   
   @NotNull
   private Long feeRecordId;
}