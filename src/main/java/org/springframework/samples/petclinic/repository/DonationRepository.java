
package org.springframework.samples.petclinic.repository;

import java.util.Collection;

import org.springframework.samples.petclinic.model.Donation;

public interface DonationRepository {

	Donation findDonationById(int id);
	Collection<Donation> findAll();

	void save(Donation donation);
	Collection<Donation> findAllByCauseId(int causeId);
}
