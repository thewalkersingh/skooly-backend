package com.skooly.backend.entity;
import com.skooly.backend.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private Double amountPaid;
   private LocalDate paymentDate;
   
   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
   private PaymentMethod paymentMethod;
   
   @ManyToOne
   @JoinColumn(name = "student_id", nullable = false)
   private Student student;
   
   @ManyToOne
   @JoinColumn(name = "fee_record_id", nullable = false)
   private FeeRecord feeRecord;
}