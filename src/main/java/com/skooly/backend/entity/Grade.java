package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "grades")
public class Grade {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "submission_id", nullable = false)
   private Submission submission;
   
   @ManyToOne
   @JoinColumn(name = "teacher_id", nullable = false)
   private Teacher gradedBy;
   private Integer score;
   private String feedback;
}