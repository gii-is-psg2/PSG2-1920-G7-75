package org.springframework.samples.petclinic.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Positive;


import com.sun.istack.NotNull;

@Entity
@Table(name = "causes")
public class Cause extends NamedEntity{

	@Column(name = "name")
	@NotNull
	private String name;
	
	@Column(name = "description")
	@NotNull
	private String desc;
	
	@Column(name = "budget_target")
	@Positive
	private Double budgetTarget;
	
	@Column(name = "organization")
	@NotNull
	private String organization;
	
	@Column(name = "donation")
	@NotNull
	private String donation;

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
	
	public String getDonation() {
		return donation;
	}
	
	public void setDonation(String donation) {
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
