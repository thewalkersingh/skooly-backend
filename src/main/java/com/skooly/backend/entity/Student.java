package com.skooly.backend.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "students")
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(nullable = false)
   private String firstName;
   
   @Column(nullable = false)
   private String lastName;
   private String address;
   
   @Column(nullable = false)
   private String contactNumber;
   
   @Column(nullable = false, unique = true)
   private String email;
   
   @Column(nullable = false)
   private String parentsName;
   
   @Column(nullable = false)
   private String grade;
   
   @Column(nullable = false)
   private Integer age;
   
   @ManyToOne
   @JoinColumn(name = "school_id")
   @JsonBackReference
   private School school;
   // Many-to-many relationship with Course.
   @ManyToMany(mappedBy = "students")
   @JsonBackReference(value = "course-student")
   private List<Course> courses = new ArrayList<>();
}