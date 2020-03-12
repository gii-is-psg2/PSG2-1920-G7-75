package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
public class PetHotel extends BaseEntity {
	
// Attributes --------------------------------------------------------------------------------
	
	@ManyToOne
	private Pet pet;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate endDate;

// Init ---------------------------------------------------------------------------------------
	
	public PetHotel(Pet pet, LocalDate startDate, LocalDate endDate) {
		this.pet = pet;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
// Getters and Setters ------------------------------------------------------------------------
	


}
