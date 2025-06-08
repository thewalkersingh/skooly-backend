package com.skooly.backend.repository;
import com.skooly.backend.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
   List<Grade> findBySubmissionId(Long submissionId);
}