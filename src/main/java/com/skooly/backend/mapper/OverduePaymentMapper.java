package com.skooly.backend.mapper;
import com.skooly.backend.dto.OverduePaymentDTO;
import com.skooly.backend.entity.OverduePayment;
import org.springframework.stereotype.Component;

@Component
public class OverduePaymentMapper {
   public OverduePaymentDTO toDto(OverduePayment overduePayment) {
	  if(overduePayment == null)
		 return null;
	  return OverduePaymentDTO.builder()
							  .id(overduePayment.getId())
							  .overdueAmount(overduePayment.getOverdueAmount())
							  .overdueDate(overduePayment.getOverdueDate())
							  .resolved(overduePayment.isResolved())
							  .studentId(
									  overduePayment.getStudent() != null ? overduePayment.getStudent().getId() : null)
							  .build();
   }
   
   // Optionally add a method for reverse mapping if needed.
}