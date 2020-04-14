package org.springframework.samples.petclinic.web;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HotelController {

    private static final String VIEWS_HOTEL_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateHotelForm";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    
    private final ClinicService clinicService;
    
    @Autowired
    public HotelController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }
    
    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
    
    @ModelAttribute("hotel")
   	public Hotel loadPetWithHotel(@PathVariable("petId") int petId) {
   		Pet pet = this.clinicService.findPetById(petId);
   		Hotel hotel = new Hotel();
   		pet.addHotel(hotel);
   		return hotel;
   	}

    @GetMapping(value = "/owners/*/pets/{petId}/hotels/new")
    public String initCreationForm(@PathVariable("petId") int petId, Map<String, Object> model) {
        return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
    }
    
    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/hotels/new")
	public String processNewHotelForm(@Valid Hotel hotel, BindingResult result) {

		if ((hotel.getStartDate() == null || hotel.getEndDate() == null)) {
			if ((hotel.getStartDate() == null && hotel.getEndDate() == null)) {
				result.rejectValue(START_DATE, "wrongStartDate", "The start date can not be empty");
				result.rejectValue(END_DATE, "wrongEndDate", "The finish date can not be empty");
			} else if ((hotel.getStartDate() == null)) {
				result.rejectValue(START_DATE, "wrongStartDate", "The start date can not be empty");
			} else {
				result.rejectValue(END_DATE, "wrongEndDate", "The finish date can not be empty");
			}
		} else if (hotel.getEndDate().isBefore(hotel.getStartDate())) {
			result.rejectValue(END_DATE, "dateStartDateAfterDateFinishDate",
					"The finish date can not be before than start date");
		}
		
		
		
		if (result.hasErrors()) {

			return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;

		} else {
			
			try {
				this.clinicService.saveHotel(hotel);
			}catch(IncorrectResultSizeDataAccessException oops) {
				result.rejectValue(END_DATE, "repeatEndDate","This period is registered for this pet");
				result.rejectValue(START_DATE, "repeatStartDate","");
				
				return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
			}
			
			return "redirect:/owners/{ownerId}";

		}
	}

    @GetMapping(value = "/owners/*/pets/{petId}/hotels")
    public String showAll(@PathVariable("petId") int petId, Map<String, Object> model) {
        model.put("hotels", this.clinicService.findPetById(petId).getHotels());
        return "hotelsList";
    }
    
}
