/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.repository.springdatajpa.SpringDataPetHotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class PetHotelService {
	
// Repository ---------------------------------------------------------------------------------------
	
	@Autowired
	private SpringDataPetHotelRepository petHotelRepository;

// Services -----------------------------------------------------------------------------------------	
	
	@Transactional()
	public Collection<PetHotel> findPetHotelsByPetId(int petId) {
		return petHotelRepository.findByPetId(petId);
	}
	
	@Transactional(readOnly = true)
	public Collection<PetHotel> findAllPetHotels() {
		return petHotelRepository.findAll();
	}
	
	@Transactional()
	public void savePetHotel(PetHotel petHotel) {
		LocalDate start = petHotel.getStartDate();
		LocalDate finish = petHotel.getEndDate();
		Collection<PetHotel> concurrent = this.petHotelRepository.concurentPetHotel(petHotel.getPet().getId(), start,finish);
		Assert.isTrue(concurrent.isEmpty(), "Cannot save another petHotel to the same pet at the same period of time");
		petHotelRepository.save(petHotel);
	}
	
	public void deletePetHotel(PetHotel petHotel)  {
		petHotelRepository.delete(petHotel);
	}
	
}
