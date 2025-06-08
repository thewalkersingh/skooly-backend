package com.skooly.backend.repository;
import com.skooly.backend.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
   List<Assignment> findByCourseId(Long courseId);
}