package com.designtech.lawyer.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.designtech.lawyer.domain.model.Lawyer;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    public Page<Lawyer> findById (Long Id, Pageable pageable);
    Page<Lawyer> findByTypeSpecialityId(Long typeSpecialityId, Pageable pageable);
    Boolean existsByTypeSpecialityId(Long typeSpecialityId);
}
