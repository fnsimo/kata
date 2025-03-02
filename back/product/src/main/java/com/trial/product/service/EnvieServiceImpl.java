package com.trial.product.service;

import org.springframework.stereotype.Service;

import com.trial.product.model.Compte;
import com.trial.product.model.Envie;
import com.trial.product.model.Product;
import com.trial.product.repository.EnvieRepository;
import com.trial.product.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnvieServiceImpl implements IEnvieService {

	private final EnvieRepository wishlistRepository;
	private final ProductRepository productRepository;

	@Transactional
	public Envie addProductToWishlist(Long compteId, Long productId) {

		Envie wishlist = wishlistRepository.findByCompteId(compteId).orElseGet(() -> createNewWishlist(compteId));

		// Trouver le produit
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));

		// Ajouter le produit à la wishlist
		wishlist.getProducts().add(product);
		return wishlistRepository.save(wishlist); // Sauvegarder la wishlist
	}

	@Transactional
	public Envie removeProductFromWishlist(Long compteId, Long productId) {

		Envie wishlist = wishlistRepository.findByCompteId(compteId)
				.orElseThrow(() -> new RuntimeException("Wishlist non trouvée"));

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));

		wishlist.getProducts().remove(product);
		return wishlistRepository.save(wishlist);
	}

	private Envie createNewWishlist(Long compteId) {
		Envie wishlist = new Envie();
		Compte compte = new Compte();
		compte.setId(compteId);
		wishlist.setCompte(compte);
		return wishlistRepository.save(wishlist);
	}

}
