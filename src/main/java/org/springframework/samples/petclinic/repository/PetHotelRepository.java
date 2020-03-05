package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.PetHotel;

public interface PetHotelRepository {
	
	void save(PetHotel hotel) throws DataAccessException;
	
	List<PetHotel> findByPetId(Integer petId);

}
