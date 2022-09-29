package com.designtech.lawyer.expertise.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.designtech.lawyer.expertise.domain.model.TypeSpeciality;

@Repository
public interface TypeSpecialityRepository extends JpaRepository<TypeSpeciality, Long> {
    public Page<TypeSpeciality> findById (Long Id, Pageable pageable);
}
