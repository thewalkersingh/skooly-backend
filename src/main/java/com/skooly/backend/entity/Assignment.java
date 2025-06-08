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
@Table(name = "assignments")
public class Assignment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String description;
   
   @Column(nullable = false)
   private LocalDate dueDate;
   
   @ManyToOne
   @JoinColumn(name = "course_id", nullable = false)
   private Course course;
}