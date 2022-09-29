package com.designtech.lawyer.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.designtech.lawyer.domain.model.Lawyer;


public interface LawyerService {
	Lawyer createLawyer(Long typeSpecialtyId, Lawyer lawyer);
    Page<Lawyer> getAllLawyersByTypeSpecialityId(Long typeSpecialtyId, Pageable pageable);
    ResponseEntity<?> deleteLawyer(Long typeSpecialtyId, Long lawyerId);
    Page<Lawyer> getAllLawyer(Pageable pageable);
}
