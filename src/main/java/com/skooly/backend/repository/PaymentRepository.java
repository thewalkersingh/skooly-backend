package com.skooly.backend.repository;
import com.skooly.backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
   List<Payment> findByStudentId(Long studentId);
}