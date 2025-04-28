package com.skooly.backend.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
   @Column(name = "street", nullable = false, length = 100)
   private String street;
   
   @Column(name = "city", nullable = false, length = 50)
   private String city;
   
   @Column(name = "state", length = 50)
   private String state;
   
   @Column(name = "pincode", length = 20)
   private String pincode;
   
   @Enumerated(EnumType.STRING)
   @Column(name = "type", length = 20)
   private AddressType type;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "student_id")
   private Student student;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "teacher_id")
   private Teacher teacher;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "schoolStaff_id")
   private SchoolStaff schoolStaff;
}