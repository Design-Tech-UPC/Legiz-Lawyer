package com.designtech.lawyer.controller;

import com.designtech.lawyer.domain.model.Lawyer;
import com.designtech.lawyer.domain.service.LawyerService;
import com.designtech.lawyer.resource.LawyerResource;
import com.designtech.lawyer.resource.SaveLawyerResource;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lawyer")
public class LawyerController {
	@Autowired
	private LawyerService lawyerService;

	@Autowired
	private ModelMapper mapper;

	@Operation(summary = "Exist Lawyer By Id", description = "Get Lawyer By Id", tags = { "lawyers" })
	@GetMapping("/existlawyer/{lawyerId}")
	public boolean existById(@PathVariable Long lawyerId) {
		return lawyerService.existById(lawyerId);

	}

	@Operation(summary = "Find Lawyer By Id", description = "Get Lawyer By Id", tags = { "lawyers" })
	@GetMapping("/findlawyer/{lawyerId}")
	public LawyerResource findById(@PathVariable Long lawyerId) {
		return convertToResource(lawyerService.findById(lawyerId));
	}

	@Operation(summary = "Create Lawyers", description = "Create Lawyers", tags = { "lawyers" })
	@PostMapping("/typespecialty/{typespecialtyId}/lawyers")
	public LawyerResource createLawyer(@PathVariable Long typespecialtyId,
			@Valid @RequestBody SaveLawyerResource resource) {
		return convertToResource(lawyerService.createLawyer(typespecialtyId, convertToEntity(resource)));
	}

	@Operation(summary = "Delete Lawyers by Type Specialty Id", description = "Delete Lawyers by Type Specialty Id", tags = {
			"lawyers" })
	@DeleteMapping("/typespecialty/{typespecialtyId}/lawyers/{lawyerId}")
	public ResponseEntity<?> deleteLawyer(@PathVariable Long typespecialtyId, @PathVariable Long lawyerId) {
		return lawyerService.deleteLawyer(typespecialtyId, lawyerId);
	}

	@Operation(summary = "Get Lawyers", description = "Get all Lawyers by Type Specialty Id", tags = { "lawyers" })
	@GetMapping("/typespecialty/{typespecialtyId}/lawyers")
	public Page<LawyerResource> getAllLawyersByTypeSpecialtyId(@PathVariable Long typespecialtyId, Pageable pageable) {
		Page<Lawyer> lawyerPage = lawyerService.getAllLawyersByTypeSpecialityId(typespecialtyId, pageable);
		List<LawyerResource> resources = lawyerPage.getContent().stream().map(this::convertToResource)
				.collect(Collectors.toList());
		return new PageImpl<>(resources, pageable, resources.size());
	}

	@Operation(summary = "Get All Lawyers", description = "Get All Lawyers", tags = { "lawyers" })
	@GetMapping("/lawyers")
	public Page<LawyerResource> getAllLawyer(Pageable pageable) {
		Page<Lawyer> lawyerPage = lawyerService.getAllLawyer(pageable);
		List<LawyerResource> resources = lawyerPage.getContent().stream().map(this::convertToResource)
				.collect(Collectors.toList());
		return new PageImpl<>(resources, pageable, resources.size());
	}

	private Lawyer convertToEntity(SaveLawyerResource resource) {
		return mapper.map(resource, Lawyer.class);
	}

	private LawyerResource convertToResource(Lawyer entity) {
		return mapper.map(entity, LawyerResource.class);
	}
}
