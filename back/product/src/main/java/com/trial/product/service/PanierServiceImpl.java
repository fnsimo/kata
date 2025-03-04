package com.trial.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trial.product.model.Compte;
import com.trial.product.model.Panier;
import com.trial.product.model.Product;
import com.trial.product.repository.PanierRepository;
import com.trial.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements IPanierService {

	private final PanierRepository panierRepository;
	private final ProductRepository productRepository;

	@Transactional
	@Override
	public Panier addProductToPanier(Long compteId, Long productId) {
		Panier panier = panierRepository.findByCompteId(compteId).orElseGet(() -> createNewPanier(compteId));

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));

		panier.getProducts().add(product);
		return panierRepository.save(panier);
	}

	@Transactional
	@Override
	public Panier removeProductFromPanier(Long compteId, Long productId) {
		Panier panier = panierRepository.findByCompteId(compteId)
				.orElseThrow(() -> new RuntimeException("Panier non trouvé"));

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Produit non trouvé"));

		panier.getProducts().remove(product);
		return panierRepository.save(panier);
	}

	private Panier createNewPanier(Long compteId) {
		Panier panier = new Panier();
		Compte compte = new Compte();
		compte.setId(compteId);
		panier.setCompte(compte);
		return panierRepository.save(panier);
	}

}
