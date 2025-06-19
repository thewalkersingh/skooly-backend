package com.skooly.backend.repository;
import com.skooly.backend.entity.OverduePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverduePaymentRepository extends JpaRepository<OverduePayment, Long> {
}