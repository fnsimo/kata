package com.trial.product.service;

import com.trial.product.model.Panier;

public interface IPanierService {

	Panier removeProductFromPanier(Long compteId, Long productId);

	Panier addProductToPanier(Long compteId, Long productId);

}
