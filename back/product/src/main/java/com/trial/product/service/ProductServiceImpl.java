package com.trial.product.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.trial.product.dto.ProductDto;
import com.trial.product.model.Product;
import com.trial.product.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

	private final ProductRepository productRepository;

	private final ModelMapper modelMapper;

	public ProductDto createProduct(ProductDto productDto) {
		productDto.setCreatedat(Instant.now());
		productDto.setUpdatedat(Instant.now());
		return modelMapper.map(productRepository.save(modelMapper.map(productDto, Product.class)), ProductDto.class);
	}

	public List<ProductDto> getAllProducts() {
		return productRepository.findAll().stream().map(compte -> modelMapper.map(compte, ProductDto.class))
				.collect(Collectors.toList());
	}

	public ProductDto getProductById(Long id) {

		Optional<Product> product = productRepository.findById(id);

		if (product.isEmpty()) {
			return null;
		}

		return modelMapper.map(product.get(), ProductDto.class);
	}

	public ProductDto updateProduct(Long id, ProductDto productUpdates) {

		Optional<Product> product = productRepository.findById(id).map(existingProduct -> {
			if (productUpdates.getCode() != null)
				existingProduct.setCode(productUpdates.getCode());
			if (productUpdates.getName() != null)
				existingProduct.setName(productUpdates.getName());
			if (productUpdates.getDescription() != null)
				existingProduct.setDescription(productUpdates.getDescription());
			if (productUpdates.getImage() != null)
				existingProduct.setImage(productUpdates.getImage());
			if (productUpdates.getCategory() != null)
				existingProduct.setCategory(productUpdates.getCategory());
			if (productUpdates.getPrice() != 0)
				existingProduct.setPrice(productUpdates.getPrice());
			if (productUpdates.getQuantity() != 0)
				existingProduct.setQuantity(productUpdates.getQuantity());
			if (productUpdates.getInternalreference() != null)
				existingProduct.setInternalreference(productUpdates.getInternalreference());
			if (productUpdates.getShellid() != 0)
				existingProduct.setShellid(productUpdates.getShellid());
			if (productUpdates.getInventorystatus() != null)
				existingProduct.setInventorystatus(productUpdates.getInventorystatus());
			if (productUpdates.getRating() != 0)
				existingProduct.setRating(productUpdates.getRating());
			existingProduct.setUpdatedat(Instant.now());
			return productRepository.save(existingProduct);
		});

		if (product.isEmpty()) {
			return null;
		}

		return modelMapper.map(product.get(), ProductDto.class);
	}

	public boolean deleteProduct(Long id) {
		if (!productRepository.existsById(id)) {
			return false;
		}
		productRepository.deleteById(id);
		return true;
	}

}
