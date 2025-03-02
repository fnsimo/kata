package com.trial.product.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trial.product.model.Envie;
import com.trial.product.service.IEnvieService;

@RestController
@RequestMapping("/api/envie")
public class EnvieController {

	private final IEnvieService wishlistService;

	public EnvieController(IEnvieService wishlistService) {
		this.wishlistService = wishlistService;
	}

	@PostMapping("/add/{compteId}/{productId}")
	public ResponseEntity<Envie> addProductToWishlist(@PathVariable Long compteId, @PathVariable Long productId) {
		Envie wishlist = wishlistService.addProductToWishlist(compteId, productId);
		return ResponseEntity.ok(wishlist);
	}

	@DeleteMapping("/remove/{compteId}/{productId}")
	public ResponseEntity<Envie> removeProductFromWishlist(@PathVariable Long compteId,
			@PathVariable Long productId) {
		Envie wishlist = wishlistService.removeProductFromWishlist(compteId, productId);
		return ResponseEntity.ok(wishlist);
	}

}
