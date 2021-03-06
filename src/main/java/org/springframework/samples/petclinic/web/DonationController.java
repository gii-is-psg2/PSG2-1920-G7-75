
package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/causes/{causeId}")
public class DonationController {

	private final ClinicService	clinicService;

	private static final String	VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM	= "donations/createOrUpdateDonationForm";


	@Autowired
	public DonationController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}
	
	@ModelAttribute("listOwners")
	public Set<Owner> populateOwners() {
		return this.clinicService.findOwners();
	}

	@InitBinder("donation")
	public void initDonationBinder(final WebDataBinder dataBinder, @PathVariable("causeId") final int causeId) {
		Cause causa = this.clinicService.findCauseById(causeId);
		dataBinder.setValidator(new DonationValidator(causa));
	}
	
	@GetMapping(value = "/newDonation")
	public String initCreationForm(@PathVariable("causeId") final int causeId, final ModelMap model) {
		Donation donation = new Donation();
		Cause cause = clinicService.findCauseById(causeId);
		model.put("donation", donation);
		model.put("cause", cause);
		return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/newDonation")
	public String processCreationForm(@Valid final Donation donation, final BindingResult result, final ModelMap model, @PathVariable("causeId") final int causeId, @RequestParam(name = "owner_id", required = true) final Integer ownerId) {
		Cause causa = this.clinicService.findCauseById(causeId);
		if(donation.getQuantity()<=0) {
			result.rejectValue("quantity","quantityError","No se permiten cantidades negativas o nulas");
		}
		if (result.hasErrors()) {
			model.put("donation", donation);
			model.put("cause", causa);
			return DonationController.VIEWS_DONATIONS_CREATE_OR_UPDATE_FORM;
		} else {
			Owner owner = this.clinicService.findOwnerById(ownerId);
			donation.setDate(LocalDate.now());
			donation.setCause(causa);
			donation.setOwner(owner);
			this.clinicService.saveDonation(donation);
			return "redirect:/causes/" + donation.getCause().getId();
		}
	}

}
