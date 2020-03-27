package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Cause;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.CauseRepository;

public interface SpringDataCauseRepository extends CauseRepository, Repository<Cause, String>{
	


}
