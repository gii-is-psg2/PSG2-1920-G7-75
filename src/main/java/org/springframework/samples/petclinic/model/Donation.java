
package org.springframework.samples.petclinic.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "cause_id")
	private Cause		cause;

	@Column(name = "donor_name")
	@NotBlank
	private String		donorName;

	@Column(name = "donation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate	date;

	@Column(name = "quantity")
	@NotNull
	@Positive
	private Integer		quantity;


	public Cause getCause() {
		return this.cause;
	}
	public void setCause(final Cause cause) {
		this.cause = cause;
	}
	public String getDonorName() {
		return this.donorName;
	}
	public void setDonorName(final String donorName) {
		this.donorName = donorName;
	}

	public LocalDate getDate() {
		return this.date;
	}
	public void setDate(final LocalDate date) {
		this.date = date;
	}
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(final Integer quantity) {
		this.quantity = quantity;
	}

}
