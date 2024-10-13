package com.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.model.Registration;

@Repository //minute 55
public interface RegistrationRepo extends JpaRepository<Registration, Long> {

	// creating methods findUserByEmail and findUserByEmailAndPassword
	public Registration findUserByEmail(String userEmail);

	public Registration findUserByEmailAndPassword(String emailId, String password);

}
