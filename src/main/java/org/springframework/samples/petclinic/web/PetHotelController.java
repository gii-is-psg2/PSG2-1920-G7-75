package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
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

@Controller
public class PetHotelController {

    private static final String VIEWS_HOTEL_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetHotelForm";
    private final ClinicService clinicService;
    
    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

    @Autowired
    public PetHotelController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/owners/*/pets/{petId}/petHotels/new")
    public String initCreationForm(@PathVariable("petId") int petId,ModelMap model) {
        return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping(value = "/owners/*/pets/{petId}/petHotels")
    public String showAll(@PathVariable int petId, Map<String, Object> model) {

        Collection<PetHotel> results = this.clinicService.findPetHotelsByPetId(petId);       
        model.put("petHotels", results);
        return "petHotelsList";
    }
    
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/petHotels/new")
	public String processNewHotelForm(@Valid PetHotel hotel, BindingResult result, @PathVariable("petId") int petId) {
		if(hotel.getEndDate().isBefore(hotel.getStartDate())) {
			result.rejectValue("endDate", "dateStartDateAfterDateFinishDate",
					"The finish date can not be before than start date");
		}
		if (result.hasErrors()) {
			return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
		}
		else {
			this.clinicService.savePetHotel(hotel);
			return "redirect:/owners/{ownerId}";
		}
	}
    
    @ModelAttribute("hotel")
	public PetHotel loadPetWithHotel(@PathVariable("petId") int petId) {
		Pet pet = this.clinicService.findPetById(petId);
		PetHotel hotel = new PetHotel();
		pet.addHotel(hotel);
		return hotel;
	}
}
