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
public class FeeRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private Double totalFee;
   private Double dueAmount;
   private LocalDate dueDate;
   private boolean fullyPaid;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
   
   @ManyToOne
   @JoinColumn(name = "course_id", nullable = false)
   private Course course;
}