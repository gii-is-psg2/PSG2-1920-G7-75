package org.springframework.samples.petclinic.web;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.Visit;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VisitValidator implements Validator{

	private List<Visit> visits = new ArrayList<Visit>();
	private Visit visit;
	
	public VisitValidator(@NotNull final Visit visit) {
		this.visit = visit;
	}
	
	@SuppressWarnings("unchecked")
	public void validate(final Object obj, final Errors errors) {
		List<Visit> visits = this.visits;
		visits = (List<Visit>) obj;
		
		Visit visit= this.visit;

		
		if (validateVisit(visit, visits)==false) {
			String message = "Cannot create the specified visit. Try another/newer date";
			errors.rejectValue("", message, message);
		}
	}
	
	public static boolean validateVisit(Visit visit, Collection<Visit> visits) {
		boolean bool = false;
		List<Visit> visitsOfAPet = new ArrayList<>(visits);

		for (int i = 0; i < visits.size(); i++) {
			if (bool) {
				break;
			} else {
				LocalDate StartDate = visitsOfAPet.get(i).getDate();
				LocalDate newStartDate = visit.getDate();

				boolean b1 = newStartDate.isBefore(StartDate);

				if (!b1) {
					bool = true;
				}
			}
		}
		return bool;
	}

		
		
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Visit.class.isAssignableFrom(clazz);
	}






}
