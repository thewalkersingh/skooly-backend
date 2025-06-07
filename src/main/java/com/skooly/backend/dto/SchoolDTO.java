package com.skooly.backend.dto;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDTO {
   private Long id;
   
   @NotBlank(message = "School name is mandatory")
   @Size(max = 100, message = "School name cannot exceed 100 characters")
   private String name;
   
   @NotBlank(message = "Address is mandatory")
   private String address;
   
   @NotBlank(message = "Contact number is mandatory")
   @Pattern(regexp = "^[0-9]{10}$", message = "Contact number must be 10 digits")
   private String contactNumber;
   
   @NotBlank(message = "Email is mandatory")
   @Email(message = "Email should be valid")
   private String email;
   
   @NotBlank(message = "School type is mandatory")
   private String schoolType;
   
   @NotNull(message = "Student count is mandatory")
   @Min(value = 0, message = "Student count cannot be negative")
   private Integer studentCount;
   private List<TeacherDTO> teachers;
}