package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "hotels")
public class PetHotel extends BaseEntity {
	
// Attributes --------------------------------------------------------------------------------
	 
	    @Column(name = "start_date")
	    @DateTimeFormat(pattern = "yyyy/MM/dd")
	    private LocalDate startDate;
	    
	    @Column(name = "end_date")
	    @DateTimeFormat(pattern = "yyyy/MM/dd")
	    private LocalDate endDate;

	    
	    @ManyToOne
	    @JoinColumn(name = "pet_id")
	    private Pet pet;

// Init ---------------------------------------------------------------------------------------
	
	public PetHotel(Pet pet, LocalDate startDate, LocalDate endDate) {
		this.pet = pet;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
// Getters and Setters ------------------------------------------------------------------------
	
	public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate start) {
        this.startDate = start;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate finish) {
        this.endDate = finish;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

}
