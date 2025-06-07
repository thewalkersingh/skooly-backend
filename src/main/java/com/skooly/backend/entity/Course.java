package com.skooly.backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(nullable = false)
   private String courseCode;  // Unique course code
   
   @Column(nullable = false)
   private String name;        // Course title
   private String description; // Optional description
   private String schedule;    // You can store schedule details as a string
   // Many courses can be assigned to one teacher.
   @ManyToOne
   @JoinColumn(name = "teacher_id")
   @JsonBackReference   // Prevents infinite recursion when serializing Teacher->Course->Teacher
   private Teacher teacher;
   // Many courses can have many students
   @ManyToMany
   @JoinTable(
		   name = "course_student",
		   joinColumns = @JoinColumn(name = "course_id"),
		   inverseJoinColumns = @JoinColumn(name = "student_id")
   )
   @JsonManagedReference(value = "course-student")
   private List<Student> students = new ArrayList<>();
}