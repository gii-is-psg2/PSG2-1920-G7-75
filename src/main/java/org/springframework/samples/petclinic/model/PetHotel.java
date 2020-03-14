package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name = "hotels")
public class PetHotel extends BaseEntity {
	
// Attributes --------------------------------------------------------------------------------
	 
	    @Column(name = "start_date")
	    @DateTimeFormat(pattern = "yyyy/MM/dd")
	    @FutureOrPresent
	    @NotNull
	    private LocalDate startDate;
	    
	    @Column(name = "end_date")
	    @DateTimeFormat(pattern = "yyyy/MM/dd")
	    @FutureOrPresent
	    @NotNull
	    private LocalDate endDate;
	    
	    @ManyToOne
	    @JoinColumn(name = "pet_id")
	    private Pet pet;
	    
 // Getters and Setters ------------------------------------------------------------------------

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public Pet getPet() {
			return pet;
		}

		public void setPet(Pet pet) {
			this.pet = pet;
		}
	

}
