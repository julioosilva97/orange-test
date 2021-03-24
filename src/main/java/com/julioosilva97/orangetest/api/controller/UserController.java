package com.julioosilva97.orangetest.api.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julioosilva97.orangetest.api.dto.UserInput;
import com.julioosilva97.orangetest.model.entities.User;
import com.julioosilva97.orangetest.model.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<Void> save(@Valid @RequestBody UserInput userInput) {

		userRepository.save(new User().toModel(userInput));

		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
