package com.trial.product.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.trial.product.model.Compte;
import com.trial.product.repository.CompteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompteServiceImpl implements UserDetailsService, ICompteService {

	private final CompteRepository compteRepository;

	public Compte createCompte(Compte compte) {
		compte.setCreatedat(Instant.now());
		return compteRepository.save(compte);
	}

	public List<Compte> getAllComptes() {
		return compteRepository.findAll();
	}

	public Optional<Compte> getCompteById(Long id) {
		return compteRepository.findById(id);
	}

	public Optional<Compte> updateCompte(Long id, Compte compteDetails) {
		return compteRepository.findById(id).map(compte -> {
			compte.setEmail(compteDetails.getEmail());
			compte.setUsername(compteDetails.getUsername());
			compte.setFirstname(compteDetails.getFirstname());
			compte.setPassword(compteDetails.getPassword());
			return compteRepository.save(compte);
		});
	}

	public boolean deleteCompte(Long id) {
		if (!compteRepository.existsById(id)) {
			return false;
		}
		compteRepository.deleteById(id);
		return true;
	}

	public Optional<Compte> findByEmail(String email) {
		return compteRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return compteRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email));
	}

}
