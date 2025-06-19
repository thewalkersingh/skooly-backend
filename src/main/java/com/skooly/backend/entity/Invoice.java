package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String invoiceNumber;
   private LocalDate issuedDate;
   private Double amountPaid;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
}