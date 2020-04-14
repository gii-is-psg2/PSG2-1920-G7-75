
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Cause;

public interface CauseRepository {

	void save(Cause cause);

	Collection<Cause> findByName(String name);

	Cause findById(int id);

	Collection<Cause> findByAll();

}
