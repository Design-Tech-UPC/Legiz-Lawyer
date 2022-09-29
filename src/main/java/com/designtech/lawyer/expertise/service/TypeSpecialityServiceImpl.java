package com.designtech.lawyer.expertise.service;

import com.designtech.lawyer.expertise.domain.repository.TypeSpecialityRepository;
import com.designtech.lawyer.expertise.domain.service.TypeSpecialityService;
import com.designtech.lawyer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.designtech.lawyer.expertise.domain.model.TypeSpeciality;
import org.springframework.stereotype.Service;

@Service
public class TypeSpecialityServiceImpl implements TypeSpecialityService {

	@Autowired
	private TypeSpecialityRepository typeSpecialityRepository;

	@Override
	public Page<TypeSpeciality> getAllTypeSpeciality(Pageable pageable) {
		return typeSpecialityRepository.findAll(pageable);
	}

	@Override
	public TypeSpeciality getTypeSpecialityById(Long typeSpecialityId) {
		return typeSpecialityRepository.findById(typeSpecialityId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId));
	}

	@Override
	public TypeSpeciality createTypeSpeciality(TypeSpeciality typeSpeciality) {
		return typeSpecialityRepository.save(typeSpeciality);
	}

	@Override
	public TypeSpeciality updateTypeSpeciality(Long typeSpecialityId, TypeSpeciality typeSpecialityRequest) {
		TypeSpeciality typeSpeciality = typeSpecialityRepository.findById(typeSpecialityId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId));
		typeSpeciality.setName(typeSpecialityRequest.getName());
		return typeSpecialityRepository.save(typeSpecialityRequest);
		//return typeSpecialityRepository.save(typeSpeciality.setName(typeSpecialityRequest.getName()));
	}

	@Override
	public ResponseEntity<?> deleteTypeSpeciality(Long typeSpecialityId) {
		TypeSpeciality typeSpeciality = typeSpecialityRepository.findById(typeSpecialityId)
				.orElseThrow(() -> new ResourceNotFoundException("Type Speciality", "Id", typeSpecialityId));
		typeSpecialityRepository.delete(typeSpeciality);
		return ResponseEntity.ok().build();
	}

}
