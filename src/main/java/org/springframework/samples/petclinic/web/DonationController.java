
package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

	private final ClinicService	clinicService;

	private static final String	VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM	= "donations/createOrUpdateDonationForm";


	@Autowired
	public DonationController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = "/causes/{causeId}/newDonation")
	public String initCreationForm(final Map<String, Object> model) {
		Donation donation = new Donation();
		model.put("donation", donation);
		return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/causes/{causeId}/newDonation")
	public String processCreationForm(@Valid final Donation donation, @PathVariable("causeId") final int causeId, final BindingResult result, final Map<String, Object> model) {
		if (result.hasErrors()) {
			model.put("donation", donation);
			return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
		} else {
			Cause causa = this.clinicService.findCauseById(causeId);
			donation.setDate(LocalDate.now());
			donation.setCause(causa);
			this.clinicService.saveDonation(donation);
			return "redirect:/causes/" + donation.getCause().getId();
		}
	}
}
