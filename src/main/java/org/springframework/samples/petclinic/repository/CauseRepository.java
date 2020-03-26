package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository {

	void save(Cause cause) throws DataAccessException;
	
	Collection<Cause> findByName(String name) throws DataAccessException;
    
}
