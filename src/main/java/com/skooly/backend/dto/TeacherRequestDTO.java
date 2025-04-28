package com.skooly.backend.dto;
import com.skooly.backend.entity.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherRequestDTO {
   private String teacherCode;
   private String firstName;
   private String lastName;
   private Gender gender;
   private LocalDate dateOfBirth;
   private LocalDate dateOfHire;
   private String email;
   private String mobileNumber;
   private Double salary;
}