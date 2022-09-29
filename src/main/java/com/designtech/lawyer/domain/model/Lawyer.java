package com.designtech.lawyer.domain.model;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.designtech.lawyer.expertise.domain.model.TypeSpeciality;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lawyer")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Lawyer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(max = 30)
	private String name;

	@NotNull
	@Size(max = 30)
	private String surname;

	@NotNull
	@Size(max = 50)
	private String email;

	@NotNull
	@Size(max = 30)
	private String password;

	@NotNull
	private Long DNI;

	@DateTimeFormat
	private LocalDate date_Birthday;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_typeSpecialty_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TypeSpeciality typeSpeciality;
}
