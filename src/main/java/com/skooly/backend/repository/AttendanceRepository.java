package com.skooly.backend.repository;

import com.skooly.backend.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
   List<Attendance> findByStudentId(Long studentId);
   List<Attendance> findByDate(LocalDate date);
}