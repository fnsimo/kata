package com.trial.product.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trial.product.dto.LoginUserDto;
import com.trial.product.dto.RegisterUserDto;
import com.trial.product.model.Compte;
import com.trial.product.repository.CompteRepository;

@Service
public class AuthenticationService {
	private final CompteRepository compteRepository;

	private final PasswordEncoder passwordEncoder;

	private final AuthenticationManager authenticationManager;

	public AuthenticationService(CompteRepository compteRepository, AuthenticationManager authenticationManager,
			PasswordEncoder passwordEncoder) {
		this.authenticationManager = authenticationManager;
		this.compteRepository = compteRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Compte signup(RegisterUserDto input) {

		if (compteRepository.existsByEmail(input.getEmail())) {
			throw new IllegalArgumentException("Email already exists");
		}

		Compte compte = new Compte();

		compte.setFirstname(input.getFirstname());
		compte.setUsername(input.getUsername());
		compte.setEmail(input.getEmail());
		compte.setPassword(passwordEncoder.encode(input.getPassword()));
		return compteRepository.save(compte);
	}

	public Compte authenticate(LoginUserDto input) {

		try {
			
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

		} catch (Exception e) {
			// Catch authentication failure and throw a custom exception
			throw new BadCredentialsException("Invalid email or password");
		}

		return compteRepository.findByEmail(input.getEmail()).orElseThrow();
	}
	

}
