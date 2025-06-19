package com.skooly.backend.dto;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceDTO {
   private Long id;
   private String invoiceNumber;
   private LocalDate issuedDate;
   private Double amountPaid;
   private Long studentId;
}