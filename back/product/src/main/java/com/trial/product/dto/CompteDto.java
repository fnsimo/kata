package com.trial.product.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompteDto {

	private Long id;
	private String email;
	private String username;
	private String firstname;
	private String password;
	private Instant createdAt;

}
