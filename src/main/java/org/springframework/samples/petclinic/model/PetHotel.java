package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "hotel")
public class PetHotel extends BaseEntity {
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "start_date")
	private LocalDate startDate;
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "finish_date")
	private LocalDate finishDate;

}
