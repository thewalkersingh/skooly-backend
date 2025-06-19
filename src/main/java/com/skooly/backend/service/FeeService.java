package com.skooly.backend.service;
import com.skooly.backend.dto.FeeRecordDTO;
import com.skooly.backend.dto.PaymentDTO;
import com.skooly.backend.entity.Course;
import com.skooly.backend.entity.FeeRecord;
import com.skooly.backend.entity.Student;
import com.skooly.backend.mapper.FeeRecordMapper;
import com.skooly.backend.repository.CourseRepository;
import com.skooly.backend.repository.FeeRecordRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeService {
   private final FeeRecordRepository feeRecordRepository;
   private final StudentRepository studentRepository;
   private final CourseRepository courseRepository;
   private final FeeRecordMapper feeRecordMapper;
   private final PaymentService paymentService;
   
   public FeeService(FeeRecordRepository feeRecordRepository, StudentRepository studentRepository,
		   CourseRepository courseRepository, FeeRecordMapper feeRecordMapper, PaymentService paymentService) {
	  this.feeRecordRepository = feeRecordRepository;
	  this.studentRepository = studentRepository;
	  this.courseRepository = courseRepository;
	  this.feeRecordMapper = feeRecordMapper;
	  this.paymentService = paymentService;
   }
   
   public PaymentDTO processPayment(PaymentDTO paymentDTO) {
	  return paymentService.processPayment(paymentDTO);
   }
   
   /**
	* Retrieves all fee records associated with a given student.
	*
	* @param studentId the ID of the student
	* @return a list of FeeRecordDTO objects
	*/
   public List<FeeRecordDTO> getFeeRecordsByStudent(Long studentId) {
	  // Validate that the student exists (optional additional validation)
	  Student student = studentRepository.findById(studentId)
										 .orElseThrow(() -> new RuntimeException("Student not found"));
	  
	  List<FeeRecord> feeRecords = feeRecordRepository.findByStudentId(studentId);
	  return feeRecords.stream()
					   .map(feeRecordMapper::toDto)
					   .collect(Collectors.toList());
   }
   
   /**
	* Creates a new fee record for a student enrolled in a course.
	*
	* @param feeRecordDTO The DTO containing fee record details.
	* @return the created FeeRecordDTO.
	*/
   public FeeRecordDTO createFeeRecord(FeeRecordDTO feeRecordDTO) {
	  // Fetch and verify associated Student and Course entities
	  Student student = studentRepository.findById(feeRecordDTO.getStudentId())
										 .orElseThrow(() -> new RuntimeException("Student not found"));
	  Course course = courseRepository.findById(feeRecordDTO.getCourseId())
									  .orElseThrow(() -> new RuntimeException("Course not found"));
	  
	  FeeRecord feeRecord = FeeRecord.builder()
									 .totalFee(feeRecordDTO.getTotalFee())
									 .dueAmount(feeRecordDTO.getDueAmount())
									 .dueDate(feeRecordDTO.getDueDate())
									 .fullyPaid(feeRecordDTO.isFullyPaid())
									 .student(student)
									 .course(course)
									 .build();
	  
	  FeeRecord savedRecord = feeRecordRepository.save(feeRecord);
	  return feeRecordMapper.toDto(savedRecord);
   }
}