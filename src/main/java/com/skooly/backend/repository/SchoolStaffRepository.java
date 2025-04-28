package com.skooly.backend.repository;
import com.skooly.backend.entity.MemberStatus;
import com.skooly.backend.entity.SchoolStaff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolStaffRepository extends JpaRepository<SchoolStaff, Long> {
   Optional<SchoolStaff> findByStaffCode(String staffCode);
   
   Page<SchoolStaff> findAllByStatus(MemberStatus status, Pageable pageable);
   
   Optional<SchoolStaff> findByEmail(String email);
}