
package org.springframework.samples.petclinic.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "causes")
public class Cause extends BaseEntity {

	@Column(name = "name")
	@NotBlank
	private String	name;

	@Column(name = "description")
	@NotBlank
	private String	desc;

	@Column(name = "budget_target")
	@Positive
	private Double	budgetTarget;

	@Column(name = "organization")
	@NotBlank
	private String	organization;
	
	@OneToMany(cascade = CascadeType.MERGE, mappedBy = "cause", fetch = FetchType.EAGER)
	private List<Donation> donations;
	
	@Column(name = "total_budget")
	@Positive
	private Double totalBudget;

	public String getName() {
		return this.name;
	}

	public void setname(final String name) {
		this.name = name;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(final String desc) {
		this.desc = desc;
	}

	public Double getBudgetTarget() {
		return this.budgetTarget;
	}

	public void setBudgetTarget(final Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public void setOrganization(final String organization) {
		this.organization = organization;
	}

	public String getOrganization() {
		return this.organization;
	}

	public List<Donation> getDonations() {
		if (this.donations == null) {
			this.donations = new ArrayList<>();
		}
		return this.donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public Double getTotalBudget() {
		Double tb = 0.;
		if(!donations.isEmpty()) {
			for(int i = 0; i < donations.size(); i++) {
				tb += donations.get(i).getQuantity();
			}
		}
		return tb;
	}

	public void setTotalBudget(Double totalBudget) {
		this.totalBudget = totalBudget;
	}
	
}
