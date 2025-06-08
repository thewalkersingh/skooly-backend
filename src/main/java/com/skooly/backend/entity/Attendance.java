package com.skooly.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendance")
public class Attendance {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
   
   @Column(nullable = false)
   private LocalDate date;
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private AttendanceStatus status;  // Present, Absent, Late, etc.
   
   private String remarks;  // Optional comments (e.g., "Sick leave")
}