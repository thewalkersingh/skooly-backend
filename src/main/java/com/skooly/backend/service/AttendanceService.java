package com.skooly.backend.service;
import com.skooly.backend.dto.AttendanceDTO;
import com.skooly.backend.entity.Attendance;
import com.skooly.backend.entity.Student;
import com.skooly.backend.exception.StudentNotFoundException;
import com.skooly.backend.mapper.AttendanceMapper;
import com.skooly.backend.repository.AttendanceRepository;
import com.skooly.backend.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AttendanceService {
   private final AttendanceRepository attendanceRepository;
   private final AttendanceMapper attendanceMapper;
   private final StudentRepository studentRepository;
   
   @Autowired
   public AttendanceService(AttendanceRepository attendanceRepository, AttendanceMapper attendanceMapper,
		   StudentRepository studentRepository) {
	  this.attendanceRepository = attendanceRepository;
	  this.attendanceMapper = attendanceMapper;
	  this.studentRepository = studentRepository;
   }
   
   public List<AttendanceDTO> getAllAttendanceRecords() {
	  return attendanceRepository.findAll()
								 .stream()
								 .map(attendanceMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public List<AttendanceDTO> getAttendanceByStudent(Long studentId) {
	  return attendanceRepository.findByStudentId(studentId)
								 .stream()
								 .map(attendanceMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public List<AttendanceDTO> getAttendanceByDate(LocalDate date) {
	  return attendanceRepository.findByDate(date)
								 .stream()
								 .map(attendanceMapper::toDTO)
								 .collect(Collectors.toList());
   }
   
   public AttendanceDTO markAttendance(AttendanceDTO attendanceDTO) {
	  Attendance attendance = attendanceMapper.toEntity(attendanceDTO);
	  Student student = studentRepository.findById(attendanceDTO.getStudentId())
										 .orElseThrow(() -> new StudentNotFoundException("Student not found"));
	  attendance.setStudent(student);
	  Attendance savedAttendance = attendanceRepository.save(attendance);
	  return attendanceMapper.toDTO(savedAttendance);
   }
   
   public void deleteAttendance(Long id) {
	  attendanceRepository.deleteById(id);
   }
}