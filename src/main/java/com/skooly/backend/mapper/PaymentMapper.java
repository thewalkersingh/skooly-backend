package com.skooly.backend.mapper;
import com.skooly.backend.dto.PaymentDTO;
import com.skooly.backend.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
   public PaymentDTO toDto(Payment payment) {
	  if(payment == null)
		 return null;
	  return PaymentDTO.builder()
					   .id(payment.getId())
					   .amountPaid(payment.getAmountPaid())
					   .paymentDate(payment.getPaymentDate())
					   .paymentMethod(payment.getPaymentMethod())
					   .studentId(payment.getStudent() != null ? payment.getStudent().getId() : null)
					   .feeRecordId(payment.getFeeRecord() != null ? payment.getFeeRecord().getId() : null)
					   .build();
   }
   
   public Payment toEntity(PaymentDTO dto) {
	  if(dto == null)
		 return null;
	  Payment payment = new Payment();
	  payment.setId(dto.getId());
	  payment.setAmountPaid(dto.getAmountPaid());
	  payment.setPaymentDate(dto.getPaymentDate());
	  payment.setPaymentMethod(dto.getPaymentMethod());
	  // You must set Student and FeeRecord entities in the service layer
	  return payment;
   }
}