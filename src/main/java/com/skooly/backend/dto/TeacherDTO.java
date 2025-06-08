package com.skooly.backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
   private Long id;
   
   @NotBlank(message = "First name is required")
   private String firstName;
   
   @NotBlank(message = "Last name is required")
   private String lastName;
   
   @NotBlank(message = "Email is required")
   @Email(message = "Email should be valid")
   private String email;
   
   @NotBlank(message = "Subject is required")
   private String subject;
   
   @NotBlank(message = "Contact number is required")
   private String contactNumber;
   
   @NotNull(message = "School ID is required")
   private Long schoolId;
   private List<Long> gradedAssignmentIds;
}