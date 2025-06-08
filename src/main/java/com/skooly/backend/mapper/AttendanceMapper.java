package com.skooly.backend.mapper;
import com.skooly.backend.dto.AttendanceDTO;
import com.skooly.backend.entity.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {
   @Mapping(source = "student.id", target = "studentId")
   @Mapping(source = "status", target = "status")
   AttendanceDTO toDTO(Attendance attendance);
   
   @Mapping(target = "student", ignore = true)
   Attendance toEntity(AttendanceDTO attendanceDTO);
   
   @Mapping(target = "student", ignore = true)
   @Mapping(target = "id", ignore = true)
   void updateAttendanceFromDTO(AttendanceDTO attendanceDTO, @MappingTarget Attendance attendance);
}