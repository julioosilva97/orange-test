package com.julioosilva97.orangetest.api.controller;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.julioosilva97.orangetest.api.dto.VaccineInput;
import com.julioosilva97.orangetest.model.entities.User;
import com.julioosilva97.orangetest.model.entities.Vaccine;
import com.julioosilva97.orangetest.model.repository.UserRepository;
import com.julioosilva97.orangetest.model.repository.VaccineRepository;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {
	
	@Autowired
	private VaccineRepository vaccineRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody VaccineInput vaccineInput ){
		
		User user = userRepository.findByEmail(vaccineInput.getUserEmail()).orElseThrow(() ->  new EntityNotFoundException("Email não cadastrado"));
		
		Vaccine vaccine = new Vaccine().toModel(vaccineInput);
		vaccine.setUser(user);
		
		vaccineRepository.save(vaccine);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
