
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.repository.CauseRepository;

public interface SpringDataCauseRepository extends CauseRepository, Repository<Cause, Integer> {

	@Override
	@Query("SELECT c FROM Cause c where c.name = ?1")
	Collection<Cause> findByName(String name) throws DataAccessException;

	@Override
	@Query("SELECT c FROM Cause c where c.id = ?1")
	Cause findById(int id) throws DataAccessException;

	@Override
	@Query("SELECT c FROM Cause c ")
	Collection<Cause> findByAll() throws DataAccessException;

}
