
package org.springframework.samples.petclinic.repository.springdatajpa;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Donation;
import org.springframework.samples.petclinic.repository.DonationRepository;

public interface SpringDataDonationRepository extends DonationRepository, Repository<Donation, Integer> {

	@Override
	@Query("SELECT d FROM Donation d where d.id = ?1")
	Donation findDonationById(int id);

	@Override
	@Query("SELECT d FROM Donation d")
	Collection<Donation> findAll();

	@Override
	@Query("SELECT d FROM Donation d where d.cause.id = ?1")
	Collection<Donation> findAllByCauseId(int causeId);
}
