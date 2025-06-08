package com.skooly.backend.entity;
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
@Table(name = "teachers")
public class Teacher {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(nullable = false)
   private String firstName;
   
   @Column(nullable = false)
   private String lastName;
   
   @Column(nullable = false, unique = true)
   private String email;
   
   @Column(nullable = false)
   private String subject;
   
   @Column(nullable = false)
   private String contactNumber;
   
   @ManyToOne
   @JoinColumn(name = "school_id")
   private School school;
   // Link to courses taught by this teacher.
   @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   @JsonManagedReference   // Managed side for Teacher->Course relationship.
   private List<Course> courses = new ArrayList<>();
   
   @OneToMany(mappedBy = "gradedBy", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private List<Grade> gradedAssignments = new ArrayList<>();
}