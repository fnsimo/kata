package com.trial.product.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trial.product.model.Panier;

@Repository
public interface PanierRepository extends JpaRepository<Panier, Long> {

	Optional<Panier> findByCompteId(Long compteId);

}
