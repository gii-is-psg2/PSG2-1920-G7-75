
package org.springframework.samples.petclinic.web;

import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DonationValidator implements Validator {

	private Cause cause;


	public DonationValidator(@NotNull final Cause cause) {
		this.cause = cause;
	}

	@Override
	public void validate(final Object obj, final Errors errors) {
		Donation donation = (Donation) obj;
		Cause causa = this.cause;
		Double moneyNeed = causa.getBudgetTarget() - causa.getTotalBudget();
		// name validation
		if (moneyNeed < donation.getQuantity()) {
			String message = "Cannot donate more than the cause needs. This cause needs " + moneyNeed + " $ more.";
			errors.rejectValue("quantity", message, message);
		}
	}

	@Override
	public boolean supports(final Class<?> clazz) {
		return Donation.class.isAssignableFrom(clazz);
	}
}
