package com.skooly.backend.service;
import com.skooly.backend.dto.OverduePaymentDTO;
import com.skooly.backend.entity.OverduePayment;
import com.skooly.backend.entity.Student;
import com.skooly.backend.mapper.OverduePaymentMapper;
import com.skooly.backend.repository.OverduePaymentRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OverduePaymentService {
   private final OverduePaymentRepository overduePaymentRepository;
   private final StudentRepository studentRepository;
   private final OverduePaymentMapper overduePaymentMapper;
   
   public OverduePaymentService(OverduePaymentRepository overduePaymentRepository, StudentRepository studentRepository,
		   OverduePaymentMapper overduePaymentMapper) {
	  this.overduePaymentRepository = overduePaymentRepository;
	  this.studentRepository = studentRepository;
	  this.overduePaymentMapper = overduePaymentMapper;
   }
   
   // ADMIN: Apply a penalty, creating an overdue payment record for a student
   public OverduePaymentDTO applyPenalty(Long studentId, Double penaltyAmount) {
	  Student student = studentRepository.findById(studentId)
										 .orElseThrow(() -> new RuntimeException("Student not found"));
	  
	  OverduePayment overduePayment = OverduePayment.builder()
													.overdueAmount(penaltyAmount)
													.overdueDate(LocalDate.now())
													.resolved(false)
													.student(student)
													.build();
	  
	  OverduePayment savedOverdue = overduePaymentRepository.save(overduePayment);
	  return overduePaymentMapper.toDto(savedOverdue);
   }
}