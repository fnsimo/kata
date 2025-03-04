package com.trial.product.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trial.product.dto.ProductDto;
import com.trial.product.service.IProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	private final IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
		ProductDto savedProduct = productService.createProduct(product);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}

	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts() {
		if (!isAdmin()) {
			throw new AccessDeniedException("Vous n'avez pas l'autorisation pour supprimer un produit.");
		}
		return ResponseEntity.ok(productService.getAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
		ProductDto productDto = productService.getProductById(id);

		if (productDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDto);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productUpdates) {

		if (!isAdmin()) {
			throw new AccessDeniedException("Vous n'avez pas l'autorisation pour modifier un produit.");
		}

		ProductDto productDto = productService.updateProduct(id, productUpdates);

		if (productDto == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productDto);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		if (!isAdmin()) {
			throw new AccessDeniedException("Vous n'avez pas l'autorisation pour supprimer un produit.");
		}

		if (!productService.deleteProduct(id)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

	private boolean isAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && "admin@admin.com".equals(authentication.getName());
	}

}
