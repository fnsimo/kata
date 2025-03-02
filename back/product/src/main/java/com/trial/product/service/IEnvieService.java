package com.trial.product.service;

import com.trial.product.model.Envie;

public interface IEnvieService {

	Envie addProductToWishlist(Long compteId, Long productId);

	Envie removeProductFromWishlist(Long compteId, Long productId);

}
