package com.trial.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trial.product.model.Compte;
import com.trial.product.service.ICompteService;

@RestController
@RequestMapping("/api/comptes")
public class CompteController {
	
	private final ICompteService compteService;

	public CompteController(ICompteService compteService) {
		this.compteService = compteService;
	}

	@PostMapping
	public ResponseEntity<Compte> createCompte(@RequestBody Compte compte) {
		return ResponseEntity.ok(compteService.createCompte(compte));
	}

	@GetMapping
	public ResponseEntity<List<Compte>> getAllComptes() {
		return ResponseEntity.ok(compteService.getAllComptes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Compte> getCompteById(@PathVariable Long id) {
		return compteService.getCompteById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Compte> updateCompte(@PathVariable Long id, @RequestBody Compte compte) {
		return compteService.updateCompte(id, compte).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCompte(@PathVariable Long id) {
		if (compteService.deleteCompte(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
