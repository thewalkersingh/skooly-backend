package com.skooly.backend.repository;
import com.skooly.backend.entity.MemberStatus;
import com.skooly.backend.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
   Optional<Teacher> findByTeacherCode(String teacherCode);
   
   Page<Teacher> findAllByStatus(MemberStatus status, Pageable pageable);
   
   Optional<Teacher> findByEmail(String email);
}