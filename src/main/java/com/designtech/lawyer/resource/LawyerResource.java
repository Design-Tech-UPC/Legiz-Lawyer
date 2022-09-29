package com.designtech.lawyer.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LawyerResource {
    private String nameSpecialty;
    private Long TypeSpecialtyId;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Long DNI;
    private LocalDate date_Birthday;
}
