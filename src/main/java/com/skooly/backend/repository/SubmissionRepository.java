package com.skooly.backend.repository;
import com.skooly.backend.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
   List<Submission> findByAssignmentId(Long assignmentId);
   
   List<Submission> findByStudentId(Long studentId);
}