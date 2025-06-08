package com.skooly.backend.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
   private Long id;
   
   @NotBlank(message = "First name is required")
   private String firstName;
   
   @NotBlank(message = "Last name is required")
   private String lastName;
   private String address;
   
   @NotBlank(message = "Contact number is required")
   private String contactNumber;
   
   @NotBlank(message = "Email is required")
   @Email(message = "Email should be valid")
   private String email;
   
   @NotBlank(message = "Parents' name is required")
   private String parentsName;
   
   @NotBlank(message = "Grade is required")
   private String grade;
   
   @NotNull(message = "Age is required")
   @Min(value = 5, message = "Age should be at least 5")
   @Max(value = 18, message = "Age should be at most 18")
   private Integer age;
   
   @NotNull(message = "School ID is required")
   private Long schoolId;
   private List<Long> submissionIds;
}