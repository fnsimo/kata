package com.trial.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trial.product.model.Compte;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

	Optional<Compte> findByEmail(String email);

	boolean existsByEmail(String email);

}
