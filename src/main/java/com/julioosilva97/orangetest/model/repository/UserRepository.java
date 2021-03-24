package com.julioosilva97.orangetest.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julioosilva97.orangetest.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByCpf(String cpf);

}
