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

package org.springframework.samples.petclinic.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.samples.petclinic.model.Hotel;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.HotelRepository;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.repository.VetRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Petclinic controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicService {

	private PetRepository	petRepository;

	private VetRepository	vetRepository;

	private OwnerRepository	ownerRepository;

	private VisitRepository	visitRepository;
	
	private HotelRepository hotelRepository;

	@Autowired
	public ClinicService(PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository,
			VisitRepository visitRepository, HotelRepository hotelRepository) {
		this.petRepository = petRepository;
		this.vetRepository = vetRepository;
		this.ownerRepository = ownerRepository;
		this.visitRepository = visitRepository;
		this.hotelRepository = hotelRepository;
	}

	@Transactional(readOnly = true)
	public Collection<PetType> findPetTypes() throws DataAccessException {
		return this.petRepository.findPetTypes();
	}

	@Transactional(readOnly = true)
	public Set<Specialty> findSpecialties() throws DataAccessException {
		return this.vetRepository.findSpecialties();
	}

	@Transactional(readOnly = true)
	public Owner findOwnerById(final int id) throws DataAccessException {
		return this.ownerRepository.findById(id);
	}

	@Transactional(readOnly = true)
	public Collection<Owner> findOwnerByLastName(final String lastName) throws DataAccessException {
		return this.ownerRepository.findByLastName(lastName);
	}

	@Transactional(readOnly = true)
	public Visit findVisitById(int id) throws DataAccessException {
		return visitRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Visit> findAll() throws DataAccessException{
		return visitRepository.findAll();

	}

	@Transactional
	public void saveOwner(final Owner owner) throws DataAccessException {
		this.ownerRepository.save(owner);
	}

	@Transactional
	public void saveVisit(final Visit visit) throws DataAccessException {
		this.visitRepository.save(visit);
	}
	public Optional<Vet> findOptionalVetById(int vetId){
		return vetRepository.findOptVetById(vetId);
	}

	@Transactional(readOnly = true)
	public Pet findPetById(final int id) throws DataAccessException {
		return this.petRepository.findById(id);
	}

	@Transactional
	public void savePet(final Pet pet) throws DataAccessException {
		this.petRepository.save(pet);
	}

	@Transactional
	public void removePet(final Pet pet) throws DataAccessException {
		this.petRepository.delete(pet);
	}
	
	@Transactional
	public void removeOwner(Owner owner) throws DataAccessException {
		ownerRepository.delete(owner);
	}

	@Transactional(readOnly = true)
	@Cacheable(value = "vets")
	public Collection<Vet> findVets() throws DataAccessException {
		return this.vetRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Vet findVetById(final int id) throws DataAccessException {
		return this.vetRepository.findVetById(id);
	}

	@Transactional
	public void saveVet(final Vet vet) throws DataAccessException {
		this.vetRepository.save(vet);
	}
	
	@Transactional
	public Collection<Visit> findVisitsByPetId(int petId) {
		return visitRepository.findByPetId(petId);
	}	
	
	@Transactional
	public void removeVisit(Visit visit) throws DataAccessException {
		visitRepository.delete(visit);
	}
    
	@Transactional
	public void removeVet(Vet vet) throws DataAccessException {
		vetRepository.delete(vet);
	}
	
	public Collection<Hotel> findHotelsByPetId(int petId) {
		return hotelRepository.findByPetId(petId);
	}
	
//	@Transactional(readOnly = true)
//	public Collection<Hotel> findAllHotels() {
//		return hotelRepository.findAll();
//	}
	
	@Transactional
	public void saveHotel(Hotel hotel) throws DataAccessException {
		
		hotelRepository.CompareBook(hotel.getPet().getId(), hotel.getStartDate(), hotel.getEndDate());
		hotelRepository.save(hotel);
			
	}
	
}
