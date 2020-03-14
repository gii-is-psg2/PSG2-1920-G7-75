package org.springframework.samples.petclinic.repository.springdatajpa;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.PetHotel;

public interface SpringDataPetHotelRepository extends JpaRepository<PetHotel, Integer> {
	
	@Query("SELECT petHotel FROM PetHotel petHotel WHERE petHotel.pet.id =:petId")
    public Collection<PetHotel> findByPetId(@Param("petId") int id);
    
    @Query("select petHotel from PetHotel petHotel where petHotel.pet.id =:petId and petHotel.start >= :start and petHotel.finish <= :finish")
    public Collection<PetHotel> concurentPetHotel(@Param("petId") int id, @Param("start") LocalDate start, @Param("finish") LocalDate finish);
}
