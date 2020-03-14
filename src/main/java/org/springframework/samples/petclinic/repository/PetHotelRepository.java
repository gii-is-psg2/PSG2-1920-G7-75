package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.PetHotel;

public interface PetHotelRepository {
	
	void save(PetHotel hotel) throws DataAccessException;
	
	List<PetHotel> findByPetId(Integer petId);

	Collection<PetHotel> findAll() throws DataAccessException;
	
	@Query("SELECT v FROM PetHotel v where v.id=:hotelId")
	PetHotel findByHotelId(@Param(value = "hotelId") int hotelId) ;
    
    @Query("SELECT p.hotels FROM Pet p where p.id=:petId")
	Collection<PetHotel> findHotelsByPetId(@Param(value = "petId") int petId) ;
	
}
