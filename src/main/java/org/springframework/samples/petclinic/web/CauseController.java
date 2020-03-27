package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CauseController {
	private static final String VIEWS_CAUSE_CREATE_FORM = "causes/createOrUpdateCauseForm";

	private final ClinicService clinicService;
	
	@Autowired
    public CauseController(ClinicService clinicService) {
        this.clinicService = clinicService;
	}
    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
    
    @GetMapping(value = "/causes/new")
	public String initCreationForm(Map<String, Object> model) {
		Cause cause = new Cause();
		model.put("cause", cause);
		return VIEWS_CAUSE_CREATE_FORM;
	}
    
    @PostMapping(value = "/causes/new")
	public String processCreationForm(@Valid Cause cause, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CAUSE_CREATE_FORM;
		}
		else {
			this.clinicService.saveCause(cause);
			return "redirect:/causes/"+cause.getId();
		}
	}
    
	@GetMapping(value = "/causes/find")
	public String initFindForm(Map<String, Object> model) {
		model.put("cause", new Cause());
		return "causes/findCauses";
	}

	@GetMapping(value = "/causes")
	public String processFindForm(Cause cause, BindingResult result, Map<String, Object> model) {

		if (cause.getName() == null) {
			cause.setname(""); 
		}

		Collection<Cause> results = this.clinicService.findCauseByName(cause.getName());
		if (results.isEmpty()) {
			result.rejectValue("Name", "notFound", "not found");
			return "causes/findCauses";
		}
		else if (results.size() == 1) {
			cause = results.iterator().next();
			return "redirect:/causes/" + cause.getId();
		}
		else {
			model.put("selections", results);
			return "causes/causesList";
		}
	}
	
    @GetMapping("/causes/{causeId}")
	public ModelAndView showCause(@PathVariable("causeId") int causeId) {
		ModelAndView mav = new ModelAndView("causes/causeDetails"); 
		mav.addObject(this.clinicService.findCauseById(causeId));
		return mav;
	}
}

