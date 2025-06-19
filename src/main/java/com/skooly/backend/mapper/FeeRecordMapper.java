package com.skooly.backend.mapper;
import com.skooly.backend.dto.FeeRecordDTO;
import com.skooly.backend.entity.FeeRecord;
import org.springframework.stereotype.Component;

@Component
public class FeeRecordMapper {
   public FeeRecordDTO toDto(FeeRecord feeRecord) {
	  if(feeRecord == null)
		 return null;
	  return FeeRecordDTO.builder()
						 .id(feeRecord.getId())
						 .totalFee(feeRecord.getTotalFee())
						 .dueAmount(feeRecord.getDueAmount())
						 .dueDate(feeRecord.getDueDate())
						 .fullyPaid(feeRecord.isFullyPaid())
						 .studentId(feeRecord.getStudent() != null ? feeRecord.getStudent().getId() : null)
						 .courseId(feeRecord.getCourse() != null ? feeRecord.getCourse().getId() : null)
						 .build();
   }
   
   // Optionally add a method to convert from DTO to Entity
}