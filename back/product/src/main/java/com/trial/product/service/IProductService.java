package com.trial.product.service;

import java.util.List;

import com.trial.product.dto.ProductDto;

public interface IProductService {

	ProductDto createProduct(ProductDto product);

	List<ProductDto> getAllProducts();

	ProductDto getProductById(Long id);

	ProductDto updateProduct(Long id, ProductDto productUpdates);

	boolean deleteProduct(Long id);

}
