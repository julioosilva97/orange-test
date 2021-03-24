package com.julioosilva97.orangetest.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.julioosilva97.orangetest.model.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
