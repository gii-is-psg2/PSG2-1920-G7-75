
package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "donations")
public class Donation {

	@Column(name = "cause")
	@NotBlank
	private String	cause;

	@Column(name = "quantity")
	@NotNull
	private Integer	quantity;
}
