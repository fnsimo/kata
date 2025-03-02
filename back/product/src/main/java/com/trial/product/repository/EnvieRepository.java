package com.trial.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trial.product.model.Envie;

@Repository
public interface EnvieRepository extends JpaRepository<Envie, Long> {

	Optional<Envie> findByCompteId(Long compteId);

}
