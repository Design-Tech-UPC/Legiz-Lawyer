package com.designtech.lawyer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.designtech.lawyer.expertise.domain.repository.TypeSpecialityRepository;
import com.designtech.lawyer.exception.ResourceNotFoundException;
import com.designtech.lawyer.domain.model.Lawyer;
import com.designtech.lawyer.domain.repository.LawyerRepository;
import com.designtech.lawyer.domain.service.LawyerService;

@Service
public class LawyerServiceImpl implements LawyerService {

	@Autowired
	private LawyerRepository lawyerRepository;

	@Autowired
	private TypeSpecialityRepository typeSpecialityRepository;

	@Override
	public Lawyer createLawyer(Long typeSpecialityId, Lawyer lawyer) {
		if (!typeSpecialityRepository.existsById(typeSpecialityId))
			throw new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId);

		return typeSpecialityRepository.findById(typeSpecialityId).map(typeSpeciality -> {
			lawyer.setTypeSpeciality(typeSpeciality);
			return lawyerRepository.save(lawyer);
		}).orElseThrow(() -> new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId));
	}

	@Override
	public Page<Lawyer> getAllLawyersByTypeSpecialityId(Long typeSpecialityId, Pageable pageable) {
		return lawyerRepository.findByTypeSpecialityId(typeSpecialityId, pageable);
	}

	@Override
	public ResponseEntity<?> deleteLawyer(Long typeSpecialityId, Long lawyerId) {
		if (!typeSpecialityRepository.existsById(typeSpecialityId))
			throw new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId);

		return lawyerRepository.findById(lawyerId).map(lawyer -> {
			lawyerRepository.delete(lawyer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Lawyer", "Id", lawyerId));
	}

	@Override
	public Page<Lawyer> getAllLawyer(Pageable pageable) {
		return lawyerRepository.findAll(pageable);
	}

	@Override
	public boolean existById(Long lawyerId) {
		return lawyerRepository.existsById(lawyerId);
	}

	@Override
	public Lawyer findById(Long lawyerId) {
		Lawyer lawyer = lawyerRepository.findById(lawyerId).orElseThrow(()->new ResourceNotFoundException("Lawyear","Id",lawyerId));
		return lawyer;
	}
}
