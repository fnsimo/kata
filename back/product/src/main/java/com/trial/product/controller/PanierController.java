package com.trial.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trial.product.model.Panier;
import com.trial.product.service.IPanierService;

@RestController
@RequestMapping("/api/panier")
public class PanierController {

	private final IPanierService panierService;

	public PanierController(IPanierService panierService) {
		this.panierService = panierService;
	}

	@PostMapping("/add/{compteId}/{productId}")
	public ResponseEntity<Panier> addProductToPanier(@PathVariable Long compteId, @PathVariable Long productId) {
		Panier panier = panierService.addProductToPanier(compteId, productId);
		return ResponseEntity.ok(panier);
	}

	@DeleteMapping("/remove/{compteId}/{productId}")
	public ResponseEntity<Panier> removeProductFromPanier(@PathVariable Long compteId, @PathVariable Long productId) {
		Panier panier = panierService.removeProductFromPanier(compteId, productId);
		return ResponseEntity.ok(panier);
	}

}
