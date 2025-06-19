package com.skooly.backend.service;
import com.skooly.backend.dto.PaymentDTO;
import com.skooly.backend.entity.FeeRecord;
import com.skooly.backend.entity.Payment;
import com.skooly.backend.entity.Student;
import com.skooly.backend.enums.PaymentMethod;
import com.skooly.backend.mapper.PaymentMapper;
import com.skooly.backend.repository.FeeRecordRepository;
import com.skooly.backend.repository.PaymentRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {
   @Autowired
   private PaymentRepository paymentRepository;
   
   @Autowired
   private FeeRecordRepository feeRecordRepository;
   
   @Autowired
   private StudentRepository studentRepository;
   
   @Autowired
   private PaymentMapper paymentMapper;
   
   // Process a student payment
   public PaymentDTO processPayment(PaymentDTO paymentDTO) {
	  // Convert DTO to entity
	  Payment payment = paymentMapper.toEntity(paymentDTO);
	  // Fetch and set associated FeeRecord and Student from the repositories
	  FeeRecord feeRecord = feeRecordRepository.findById(paymentDTO.getFeeRecordId())
											   .orElseThrow(() -> new RuntimeException("Fee record not found"));
	  Student student = studentRepository.findById(paymentDTO.getStudentId())
										 .orElseThrow(() -> new RuntimeException("Student not found"));
	  payment.setFeeRecord(feeRecord);
	  payment.setStudent(student);
	  
	  // Validate installment: minimum 10% of the total fee
	  double minInstallment = feeRecord.getTotalFee() * 0.10;
	  if(payment.getAmountPaid() < minInstallment){
		 throw new IllegalArgumentException("Installment must be at least 10% of total fee.");
	  }
	  // Restrict cash payments above a certain threshold (₹50,000 in this case)
	  if(payment.getPaymentMethod() == PaymentMethod.CASH && payment.getAmountPaid() > 50000){
		 throw new IllegalArgumentException("Cash payments above ₹50,000 are not allowed.");
	  }
	  Payment savedPayment = paymentRepository.save(payment);
	  return paymentMapper.toDto(savedPayment);
   }
   
   // ADMIN: Adjust payment for corrections
   public PaymentDTO adjustPayment(Long paymentId, Double newAmount, PaymentMethod newMethod) {
	  Payment payment = paymentRepository.findById(paymentId)
										 .orElseThrow(() -> new RuntimeException("Payment not found"));
	  
	  payment.setAmountPaid(newAmount);
	  payment.setPaymentMethod(newMethod);
	  
	  Payment updatedPayment = paymentRepository.save(payment);
	  return paymentMapper.toDto(updatedPayment);
   }
   
   // ADMIN: Get a paginated list of all payments
   public Page<PaymentDTO> getAllPayments(Pageable pageable) {
	  return paymentRepository.findAll(pageable)
							  .map(paymentMapper::toDto);
   }
   
   public List<PaymentDTO> getPaymentsByStudent(Long studentId) {
	  List<Payment> payments = paymentRepository.findByStudentId(studentId);
	  return payments.stream()
					 .map(paymentMapper::toDto)
					 .collect(Collectors.toList());
   }
}