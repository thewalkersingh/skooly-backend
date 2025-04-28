package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "teachers", uniqueConstraints = @UniqueConstraint(columnNames = "teacher_code"))
public class Teacher {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "teacher_code", nullable = false, unique = true, length = 20)
   private String teacherCode;
   
   @Column(name = "first_name", nullable = false, length = 50)
   private String firstName;
   
   @Column(name = "last_name", nullable = false, length = 50)
   private String lastName;
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private Gender gender;
   
   @Column(name = "date_of_birth", nullable = false)
   private LocalDate dateOfBirth;
   
   @Column(name = "date_of_hire", nullable = false)
   private LocalDate dateOfHire;
   
   @Column(nullable = false, length = 100)
   private String email;
   
   @Column(name = "mobile_number", length = 15)
   private String mobileNumber;
   
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private MemberStatus status;
   
   @Column
   private Double salary;
   
   @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<Address> addresses = new HashSet<>();
}