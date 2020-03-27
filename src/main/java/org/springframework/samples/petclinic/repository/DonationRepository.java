
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository {

	Donation findDonationById(int id) throws DataAccessException;
	Collection<Donation> findAll() throws DataAccessException;

	void save(Donation donation) throws DataAccessException;
	Collection<Donation> findAllByCauseId(int causeId) throws DataAccessException;
}
