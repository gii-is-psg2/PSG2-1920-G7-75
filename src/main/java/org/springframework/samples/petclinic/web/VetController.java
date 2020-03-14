/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
 Task-Create-Edit-Vet
import org.springframework.samples.petclinic.model.Specialty;

 develop1
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.ui.ModelMap;
Task-Create-Edit-Vet
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

  
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Optional;

 develop1
/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final ClinicService	clinicService;

	private static final String	VIEWS_VETS_CREATE_OR_UPDATE_FORM	= "vets/createOrUpdateVetForm";


	@Autowired
	public VetController(final ClinicService clinicService) {
		this.clinicService = clinicService;
	}

	@ModelAttribute("listSpecialties")
	public Collection<Specialty> populateSpecialties() {
		return this.clinicService.findSpecialties();
	}

	@GetMapping(value = {
		"/vets"
	})
	public String showVetList(final Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = {
		"/vets.xml"
	})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.clinicService.findVets());
		return vets;
	}
 Task-Create-Edit-Vet

	@GetMapping(value = "/vets/new")
	public String initCreationForm(final ModelMap model) {
		Vet vet = new Vet();
		model.put("vet", vet);
		model.addAttribute("specialties", new HashSet<>());
		return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/new")
	public String processCreationForm(@Valid final Vet vet, @RequestParam(name = "listaSpe", required = false) final String specialties, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
		} else {
			if (specialties != null) {
				String[] speNames = specialties.split(",");
				Set<Specialty> selectedSpe = new HashSet<>();
				for (String s : speNames) {
					selectedSpe.add((Specialty) this.populateSpecialties().stream().filter(x -> x.getName().equals(s)).toArray()[0]);
				}
				for (Specialty spe : selectedSpe) {
					vet.addSpecialty(spe);
				}
			}
			this.clinicService.saveVet(vet);
			return "redirect:/vets";
		}
	}

	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateForm(@PathVariable("vetId") final int vetId, final ModelMap model) {
		Vet vet = this.clinicService.findVetById(vetId);
		model.put("vet", vet);
		return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateForm(@Valid final Vet vet, @RequestParam(name = "listaSpe", required = false) final String specialties, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
		} else {
			if (specialties != null) {
				String[] speNames = specialties.split(",");
				Set<Specialty> selectedSpe = new HashSet<>();
				for (String s : speNames) {
					selectedSpe.add((Specialty) this.populateSpecialties().stream().filter(x -> x.getName().equals(s)).toArray()[0]);
				}
				for (Specialty spe : selectedSpe) {
					vet.addSpecialty(spe);
				}
			}
			this.clinicService.saveVet(vet);
			return "redirect:/vets";
		}
	}


	@GetMapping(value = "/vets/new")
	public String initCreationForm(final ModelMap model) {
		Vet vet = new Vet();
		model.put("vet", vet);
		model.addAttribute("specialties", new HashSet<>());
		return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/new")
	public String processCreationForm(@Valid final Vet vet, @RequestParam(name = "listaSpe", required = false) final String specialties, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
		} else {
			if (specialties != null) {
				String[] speNames = specialties.split(",");
				Set<Specialty> selectedSpe = new HashSet<>();
				for (String s : speNames) {
					selectedSpe.add((Specialty) this.populateSpecialties().stream().filter(x -> x.getName().equals(s)).toArray()[0]);
				}
				for (Specialty spe : selectedSpe) {
					vet.addSpecialty(spe);
				}
			}
			this.clinicService.saveVet(vet);
			return "redirect:/vets";
		}
	}

	@GetMapping(value = "/vets/{vetId}/edit")
	public String initUpdateForm(@PathVariable("vetId") final int vetId, final ModelMap model) {
		Vet vet = this.clinicService.findVetById(vetId);
		model.put("vet", vet);
		return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/vets/{vetId}/edit")
	public String processUpdateForm(@Valid final Vet vet, @RequestParam(name = "listaSpe", required = false) final String specialties, final BindingResult result, final ModelMap model) {
		if (result.hasErrors()) {
			model.put("vet", vet);
			return VetController.VIEWS_VETS_CREATE_OR_UPDATE_FORM;
		} else {
			if (specialties != null) {
				String[] speNames = specialties.split(",");
				Set<Specialty> selectedSpe = new HashSet<>();
				for (String s : speNames) {
					selectedSpe.add((Specialty) this.populateSpecialties().stream().filter(x -> x.getName().equals(s)).toArray()[0]);
				}
				for (Specialty spe : selectedSpe) {
					vet.addSpecialty(spe);
				}
			}
			this.clinicService.saveVet(vet);
			return "redirect:/vets";
		}
	}
	@RequestMapping(value = "/vet/{vetId}/delete")
	public String deleteVet(@PathVariable("vetId") final int vetId, final ModelMap model) {
		Optional<Vet> vet= this.clinicService.findOptionalVetById(vetId);
		this.clinicService.removeVet(vet.get());
		return "redirect:/vets";
	}
develop1
}
