package com.skooly.backend.controller;
import com.skooly.backend.dto.FeeRecordDTO;
import com.skooly.backend.dto.PaymentDTO;
import com.skooly.backend.service.FeeService;
import com.skooly.backend.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fees")
public class FeeController {
   private final FeeService feeService;
   private final PaymentService paymentService;
   
   public FeeController(FeeService feeService, PaymentService paymentService) {
	  this.feeService = feeService;
	  this.paymentService = paymentService;
   }
   
   // Student: Get fee records for a specific student
   @GetMapping("/student/{studentId}")
   public ResponseEntity<List<FeeRecordDTO>> getStudentFeeRecords(@PathVariable Long studentId) {
	  return ResponseEntity.ok(feeService.getFeeRecordsByStudent(studentId));
   }
   
   // Student: Process a payment (installment)
   @PostMapping("/payment")
   public ResponseEntity<PaymentDTO> processPayment(@RequestBody PaymentDTO paymentDTO) {
	  return ResponseEntity.ok(feeService.processPayment(paymentDTO));
   }
   
   // Student: Fetch payment history
   @GetMapping("/payments/student/{studentId}")
   public ResponseEntity<List<PaymentDTO>> getPaymentHistory(@PathVariable Long studentId) {
	  return ResponseEntity.ok(paymentService.getPaymentsByStudent(studentId));
   }
}