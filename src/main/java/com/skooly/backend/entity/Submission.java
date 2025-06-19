package com.skooly.backend.entity;
import com.skooly.backend.enums.SubmissionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "submissions")
public class Submission {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @ManyToOne
   @JoinColumn(name = "assignment_id", nullable = false)
   private Assignment assignment;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
   private String fileUrl; // URL of the submitted assignment file
   
   @Column(nullable = false)
   private LocalDateTime submittedAt;
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private SubmissionStatus status;
}