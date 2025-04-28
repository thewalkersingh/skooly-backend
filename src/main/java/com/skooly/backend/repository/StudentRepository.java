package com.skooly.backend.repository;
import com.skooly.backend.entity.MemberStatus;
import com.skooly.backend.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
   Optional<Student> findByStudentCode(String studentCode);
   
   Page<Student> findAllByStatus(MemberStatus status, Pageable pageable);
   
   Page<Student> findAllByFirstNameContainingIgnoreCase(String name, Pageable pageable);
   
   Optional<Student> findByEmail(String email);
}