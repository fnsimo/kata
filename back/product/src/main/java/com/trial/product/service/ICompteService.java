package com.trial.product.service;

import java.util.List;
import java.util.Optional;

import com.trial.product.model.Compte;

public interface ICompteService {

	Compte createCompte(Compte compte);

	List<Compte> getAllComptes();

	Optional<Compte> getCompteById(Long id);

	Optional<Compte> updateCompte(Long id, Compte compte);

	boolean deleteCompte(Long id);

	Optional<Compte> findByEmail(String email);

}
