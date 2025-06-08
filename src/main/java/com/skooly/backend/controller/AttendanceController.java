package com.skooly.backend.controller;
import com.skooly.backend.dto.AttendanceDTO;
import com.skooly.backend.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
   private final AttendanceService attendanceService;
   
   @Autowired
   public AttendanceController(AttendanceService attendanceService) {
	  this.attendanceService = attendanceService;
   }
   
   @GetMapping("/all")
   public List<AttendanceDTO> getAllAttendanceRecords() {
	  return attendanceService.getAllAttendanceRecords();
   }
   
   @GetMapping("/student/{studentId}")
   public List<AttendanceDTO> getAttendanceByStudent(@PathVariable Long studentId) {
	  return attendanceService.getAttendanceByStudent(studentId);
   }
   
   @GetMapping("/date/{date}")
   public List<AttendanceDTO> getAttendanceByDate(@PathVariable LocalDate date) {
	  return attendanceService.getAttendanceByDate(date);
   }
   
   @PostMapping
   public AttendanceDTO markAttendance(@Valid @RequestBody AttendanceDTO attendanceDTO) {
	  return attendanceService.markAttendance(attendanceDTO);
   }
   
   @DeleteMapping("/{id}")
   public void deleteAttendance(@PathVariable Long id) {
	  attendanceService.deleteAttendance(id);
   }
}