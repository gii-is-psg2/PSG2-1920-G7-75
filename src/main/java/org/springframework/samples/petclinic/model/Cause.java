package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity{

	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "description")
	@NotBlank
	private String desc;
	
	@Column(name = "budget_target")
	@Positive
	private Double budgetTarget;
	
	@Column(name = "organization")
	@NotBlank
	private String organization;
	
	@Column(name = "donation")
	@Positive
	private Double donation;

//	@ManyToOne
//	@JoinColumn(name = "donation")
//	private Donation donation;

	
	
	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Double getBudgetTarget() {
		return budgetTarget;
	}
	
	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}
	
	public Double getDonation() {
		return donation;
	}
	
	public void setDonation(Double donation) {
		this.donation = donation;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

//	public Donation getDonation() {
//		return donation;
//	}
//
//	public void setDonation(Donation donation) {
//		this.donation = donation;
//	}
}
