package com.skooly.backend.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "schools")
public class School {
   @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
   private String name;
   private String address;
   private String contactNumber;
   private String email;
   // Using Java 8 Date/Time APIs for better clarity and features.
   private LocalDateTime creationDate;
   private String schoolType;
   // Representing studentCount as a number
   private Integer studentCount;
   // Relationship with Student entities (ensure you define a matching mapping in Student)
   @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Student> students = new ArrayList<>();
   // Add a one-to-many mapping to teachers.
   @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JsonManagedReference  // Helps to avoid infinite recursion when serializing to JSON
   private List<Teacher> teachers = new ArrayList<>();
}