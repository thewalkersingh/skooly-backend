package com.skooly.backend.dto;
import com.skooly.backend.entity.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class StudentRequestDTO {
   private String studentCode;
   private String firstName;
   private String lastName;
   private Gender gender;
   private LocalDate dateOfBirth;
   private LocalDate dateOfAdmission;
   private String email;
   private String mobileNumber;
   private String parentName;
   private String parentNumber;
   private Set<CoreSubject> coreSubjects;
   private Set<ElectiveSubject> electiveSubjects;
   private Set<Language> languages;
   private Set<SkillSubject> skillSubjects;
}