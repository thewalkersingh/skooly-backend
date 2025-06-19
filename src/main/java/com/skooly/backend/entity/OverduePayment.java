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
public class OverduePayment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private Double overdueAmount;
   private LocalDate overdueDate;
   private boolean resolved;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
}