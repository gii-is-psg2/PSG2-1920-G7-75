package org.springframework.samples.petclinic.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "cause", fetch = FetchType.EAGER)
	private Set<Donation> donations;
	
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
	
	public Set<Donation> getDonations() {
		if (this.donations == null) {
			this.donations = new HashSet<>();
		}
		return this.donations;
	}
	
	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrganization() {
		return organization;
	}

}
