package com.designtech.lawyer.expertise.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.designtech.lawyer.expertise.domain.model.TypeSpeciality;
import com.designtech.lawyer.expertise.domain.service.TypeSpecialityService;
import com.designtech.lawyer.expertise.resource.SaveTypeSpecialityResource;
import com.designtech.lawyer.expertise.resource.TypeSpecialityResource;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api")
public class TypeSpecialityController {
    @Autowired
	private TypeSpecialityService typeSpecialityService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary="Update Type Speciality", description="Update Type Speciality", tags={"typespecialitys"})
    @PutMapping("/typespecialitys/{typespecialityId}")
    public TypeSpecialityResource updateTypeSpeciality(
            @PathVariable Long typespecialityId,
            @Valid @RequestBody SaveTypeSpecialityResource resource){
        TypeSpeciality typeSpeciality = convertToEntity(resource);
        return convertToResource(typeSpecialityService.updateTypeSpeciality(typespecialityId,typeSpeciality));
    }

    @Operation(summary = "Get All Type Speciality", description = "Get All Type Speciality", tags = {"typespecialitys"})
    @GetMapping("/typespecialitys")
    public Page<TypeSpecialityResource> getAllTypeSpeciality(Pageable pageable){
        Page<TypeSpeciality> typeSpecialityPage = typeSpecialityService.getAllTypeSpeciality(pageable);
        List<TypeSpecialityResource> resources = typeSpecialityPage.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources,pageable, resources.size());
    }

    @Operation(summary = "Post Type Speciality", description = "Post Type Speciality", tags = {"typespecialitys"})
    @PostMapping("/typespecialitys")
    public TypeSpecialityResource createTypeSpeciality(@Valid @RequestBody SaveTypeSpecialityResource resource){
        TypeSpeciality typeSpeciality = convertToEntity(resource);
        return  convertToResource(typeSpecialityService.createTypeSpeciality(typeSpeciality));
    }

    @Operation(summary = "Get Type Speciality by Id", description = "Get Type Speciality by Id", tags = {"typespecialitys"})
    @GetMapping("/typespecialitys/{typespecialityId}")
    public TypeSpecialityResource getTypeSpecialityById(@PathVariable Long typespecialityId){
        return convertToResource(typeSpecialityService.getTypeSpecialityById(typespecialityId));
    }

    @Operation(summary = "Delete Type Speciality", description = "Delete Type Speciality", tags = {"typespecialitys"})
    @DeleteMapping("/typespecialitys/{typespecialityId}")
    public ResponseEntity<?> deleteTypeSpeciality(@PathVariable Long typespecialityId) {
        return typeSpecialityService.deleteTypeSpeciality(typespecialityId);
    }

    private TypeSpeciality convertToEntity(SaveTypeSpecialityResource resource){
        return mapper.map(resource, TypeSpeciality.class);
    }

    private TypeSpecialityResource convertToResource(TypeSpeciality entity){
        return mapper.map(entity, TypeSpecialityResource.class);
    }
}