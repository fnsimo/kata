package com.trial.product.dto;

import java.time.Instant;

import com.trial.product.model.InventoryStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

	private Long id;
	private String code;
	private String name;
	private String description;
	private String image;
	private String category;
	private double price;
	private int quantity;
	private String internalreference;
	private int shellid;
	@Enumerated(EnumType.STRING)
	private InventoryStatus inventorystatus;
	private int rating;

	@Temporal(TemporalType.TIMESTAMP)
	private Instant createdat;

	@Temporal(TemporalType.TIMESTAMP)
	private Instant updatedat;

}
