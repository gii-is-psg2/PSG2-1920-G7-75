package org.springframework.samples.petclinic.repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.Hotel;

public interface HotelRepository {
	
	void save(Hotel hotel);
	
	List<Hotel> findByPetId(Integer petId);
	
	@Query("SELECT v FROM Hotel v where v.id=:hotelId")
	Hotel findByHotelId(@Param(value = "hotelId") int hotelId);
    
    @Query("SELECT p.hotels FROM Pet p where p.id=:petId")
	Collection<Hotel> findHotelsByPetId(@Param(value = "petId") int petId);
    
    @Query("SELECT h FROM Hotel h WHERE h.pet.id=:petId AND ((h.startDate >=:start AND h.startDate <=:end) "
    		+ "OR (h.startDate <=:start AND h.endDate >=:start)"
    		+ "OR (h.startDate <=:end AND h.endDate >=:end))")
    Hotel compareBook(@Param(value = "petId") int petId,@Param(value = "start") LocalDate start, @Param(value = "end") LocalDate end);
	
}
