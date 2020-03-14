package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.service.PetHotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PetHotelController {

    private static final String VIEWS_HOTEL_CREATE_OR_UPDATE_FORM = "petHotels/createOrUpdatePetHotelForm";
    private final PetHotelService petHotelService;
    private final ClinicService clinicService;

    @Autowired
    public PetHotelController(PetHotelService petHotelService, ClinicService clinicService) {
        this.petHotelService = petHotelService;
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/petHotels/{petId}/new")
    public String initCreationForm(@PathVariable("petId") int petId,ModelMap model) {
        PetHotel petHotel = new PetHotel(null,null,null);
        Pet pet=clinicService.findPetById(petId);
        petHotel.setPet(pet);
        model.put("petHotel", petHotel);        
        return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
    }

    @GetMapping(value = "/petHotels")
    public String showAll( Map<String, Object> model) {

        Collection<PetHotel> results = this.petHotelService.findAllPetHotels();       
        model.put("petHotels", results);
        return "petHotels/petHotelsList";
    }
    
    @PostMapping(value = "/petHotels/{petId}/new")
    public String creationForm(@Valid PetHotel petHotel,@PathVariable("petId") int petId, BindingResult result) {
        Pet pet=clinicService.findPetById(petId);
        petHotel.setPet(pet);
        if (result.hasErrors()) {
            return VIEWS_HOTEL_CREATE_OR_UPDATE_FORM;
        } else {
            this.petHotelService.savePetHotel(petHotel);
            return "redirect:/petHotels";
        }
    }
}
