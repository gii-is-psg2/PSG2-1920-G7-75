
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
}
