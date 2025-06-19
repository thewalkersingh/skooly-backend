package com.skooly.backend.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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
   private LocalDate creationDate;
   private String schoolType;
   private Integer studentCount;
   
   @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Student> students = new ArrayList<>();
   
   @OneToMany(mappedBy = "school", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JsonManagedReference  // Helps to avoid infinite recursion when serializing to JSON
   private List<Teacher> teachers = new ArrayList<>();
}