package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class PetHotelController {
	
	private final ClinicService clinicService;

	@Autowired
	public PetHotelController(ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@ModelAttribute("hotel")
	public PetHotel loadPetWithHotel(@PathVariable("petId") int petId) {
		Pet pet = this.clinicService.findPetById(petId);
		PetHotel hotel = new PetHotel();
		pet.addHotel(hotel);
		return hotel;
	}

	@GetMapping(value = "/owners/*/pets/{petId}/hotels/new")
	public String initNewHotelForm(@PathVariable("petId") int petId, Map<String, Object> model) {
		return "pets/createOrUpdateVisitForm";
	}

	@PostMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/new")
	public String processNewHotelForm(@Valid PetHotel hotel, BindingResult result) {
		if (result.hasErrors()) {
			return "pets/createOrUpdateVisitForm";
		}
		else {
			this.clinicService.saveVisit(visit);
			return "redirect:/owners/{ownerId}";
		}
	}

	@GetMapping(value = "/owners/*/pets/{petId}/visits")
	public String showVisits(@PathVariable int petId, Map<String, Object> model) {
		model.put("visits", this.clinicService.findPetById(petId).getVisits());
		return "visitList";
	}

}
