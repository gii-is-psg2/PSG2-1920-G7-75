
package org.springframework.samples.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DonationController {

	private final ClinicService	clinicService;

	private static final String	VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM	= "donations/createOrUpdateDonationForm";


	@Autowired
	public DonationController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@GetMapping(value = "/donations/new")
	public String initCreationForm(final ModelMap model) {
		Donation donation = new Donation();
		model.put("donation", donation);
		return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/donations/new")
	public String processCreationForm(@Valid final Donation donation, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("donation", donation);
			return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
		} else {
			this.clinicService.saveDonation(donation);
			return "redirect:/vets";
		}
	}
}
