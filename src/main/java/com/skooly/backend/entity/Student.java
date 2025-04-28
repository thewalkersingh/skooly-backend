package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Student {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   
   @Column(name = "student_code", nullable = false, unique = true, length = 20)
   private String studentCode;
   
   @Column(name = "first_name", nullable = false, length = 50)
   private String firstName;
   
   @Column(name = "last_name", nullable = false, length = 50)
   private String lastName;
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private Gender gender;
   
   @Column(name = "date_of_birth", nullable = false)
   private LocalDate dateOfBirth;
   
   @Column(name = "date_of_admission", nullable = false)
   private LocalDate dateOfAdmission;
   
   @Column(nullable = false, length = 100)
   private String email;
   
   @Column(name = "mobile_number", length = 15)
   private String mobileNumber;
   
   @Column(name = "parent_name", length = 100)
   private String parentName;
   
   @Column(name = "parent_number", length = 15)
   private String parentNumber;
   
   @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
   private Set<Address> addresses = new HashSet<>();
   
   @ElementCollection(targetClass = CoreSubject.class)
   @Enumerated(EnumType.STRING)
   @CollectionTable(name = "student_core_subjects", joinColumns = @JoinColumn(name = "student_id"))
   @Column(name = "core_subject")
   private Set<CoreSubject> coreSubjects = new HashSet<>();
   
   @ElementCollection(targetClass = ElectiveSubject.class)
   @Enumerated(EnumType.STRING)
   @CollectionTable(name = "student_elective_subjects", joinColumns = @JoinColumn(name = "student_id"))
   @Column(name = "elective_subject")
   private Set<ElectiveSubject> electiveSubjects = new HashSet<>();
   
   @ElementCollection(targetClass = Language.class)
   @Enumerated(EnumType.STRING)
   @CollectionTable(name = "student_languages", joinColumns = @JoinColumn(name = "student_id"))
   @Column(name = "language")
   private Set<Language> languages = new HashSet<>();
   
   @ElementCollection(targetClass = SkillSubject.class)
   @Column(name = "skill_subject")
   @Enumerated(EnumType.STRING)
   @CollectionTable(name = "student_skill_subjects", joinColumns = @JoinColumn(name = "student_id"))
   private Set<SkillSubject> skillSubjects = new HashSet<>();
   
   @Column(nullable = false)
   @Enumerated(EnumType.STRING)
   private MemberStatus status;
}