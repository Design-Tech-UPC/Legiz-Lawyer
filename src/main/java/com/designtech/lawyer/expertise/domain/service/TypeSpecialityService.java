package com.designtech.lawyer.expertise.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.designtech.lawyer.expertise.domain.model.TypeSpeciality;


public interface TypeSpecialityService {
    Page<TypeSpeciality> getAllTypeSpeciality(Pageable pageable);
    TypeSpeciality getTypeSpecialityById(Long typeSpecialityId);
    TypeSpeciality createTypeSpeciality(TypeSpeciality typeSpeciality);
    TypeSpeciality updateTypeSpeciality(Long typeSpecialityId, TypeSpeciality typeSpecialityRequest);
    ResponseEntity<?> deleteTypeSpeciality(Long typeSpecialityId);
}
