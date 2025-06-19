package com.skooly.backend.controller;
import com.skooly.backend.dto.OverduePaymentDTO;
import com.skooly.backend.dto.PaymentDTO;
import com.skooly.backend.enums.PaymentMethod;
import com.skooly.backend.service.OverduePaymentService;
import com.skooly.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
   @Autowired
   private PaymentService paymentService;
   
   @Autowired
   private OverduePaymentService overduePaymentService;
   
   // ADMIN: Adjust payment manually (for corrections)
   @PutMapping("/payments/adjust/{paymentId}")
   public ResponseEntity<PaymentDTO> adjustPayment(
		   @PathVariable Long paymentId,
		   @RequestParam Double newAmount,
		   @RequestParam PaymentMethod newMethod) {
	  PaymentDTO adjustedPayment = paymentService.adjustPayment(paymentId, newAmount, newMethod);
	  return ResponseEntity.ok(adjustedPayment);
   }
   
   // ADMIN: Apply penalty for overdue payments
   @PostMapping("/overdue/{studentId}")
   public ResponseEntity<OverduePaymentDTO> applyPenalty(
		   @PathVariable Long studentId,
		   @RequestParam Double penaltyAmount) {
	  OverduePaymentDTO overduePaymentDTO = overduePaymentService.applyPenalty(studentId, penaltyAmount);
	  return ResponseEntity.ok(overduePaymentDTO);
   }
   
   // ADMIN: Get paginated finance report (all payments)
   @GetMapping("/payments/report")
   public ResponseEntity<Page<PaymentDTO>> getFinanceReport(Pageable pageable) {
	  Page<PaymentDTO> report = paymentService.getAllPayments(pageable);
	  return ResponseEntity.ok(report);
   }
   
   // -----------------------------------------------------------
   // Place additional admin endpoints here for student/teacher/school
   // For example:
   //
   // @PostMapping("/students")
   // public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentDTO studentDTO) { ... }
   //
   // @DeleteMapping("/teachers/{teacherId}")
   // public ResponseEntity<Void> deleteTeacher(@PathVariable Long teacherId) { ... }
   //
   // @PutMapping("/schools/{schoolId}")
   // public ResponseEntity<SchoolDTO> editSchool(@PathVariable Long schoolId, @RequestBody SchoolDTO schoolDTO) { ... }
   // -----------------------------------------------------------
}