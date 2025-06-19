package com.skooly.backend.repository;
import com.skooly.backend.entity.FeeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeeRecordRepository extends JpaRepository<FeeRecord, Long> {
   List<FeeRecord> findByStudentId(Long studentId);
}