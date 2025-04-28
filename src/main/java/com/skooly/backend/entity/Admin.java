package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "admins")
public class Admin {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String username;
   private String password;
   
   @Enumerated(EnumType.STRING)
//   private Role role = Role.ADMIN; // default role ADMIN
   private Role role;
}