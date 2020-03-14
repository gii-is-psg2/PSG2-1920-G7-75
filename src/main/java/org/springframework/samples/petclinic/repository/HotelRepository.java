package org.springframework.samples.petclinic.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Hotel;

public interface HotelRepository {
	
	void save(Hotel hotel) throws DataAccessException;
	
	List<Hotel> findByPetId(Integer petId);

//	Collection<Hotel> findAll() throws DataAccessException;
	
	@Query("SELECT v FROM Hotel v where v.id=:hotelId")
	Hotel findByHotelId(@Param(value = "hotelId") int hotelId);
    
    @Query("SELECT p.hotels FROM Pet p where p.id=:petId")
	Collection<Hotel> findHotelsByPetId(@Param(value = "petId") int petId);
	
}
