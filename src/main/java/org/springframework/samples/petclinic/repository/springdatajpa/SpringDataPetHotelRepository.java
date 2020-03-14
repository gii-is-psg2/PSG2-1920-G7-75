package org.springframework.samples.petclinic.repository.springdatajpa;


import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.PetHotel;
import org.springframework.samples.petclinic.repository.PetHotelRepository;

public interface SpringDataPetHotelRepository extends PetHotelRepository, Repository<PetHotel, Integer> {
	
}
