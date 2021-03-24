package com.julioosilva97.orangetest.model.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.julioosilva97.orangetest.api.dto.UserInput;

@Entity
@Table(name = "user")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 150)
	private String name;

	@Column(nullable = false, length = 150)
	private String email;

	@Column(nullable = false, length = 11)
	private String cpf;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public User() {
	}

	public User(Integer id, String name, String email, String cpf, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birthDate = birthDate;
	}
	
	public User toModel(UserInput user) {
		
		name = user.getName();
		email = user.getEmail();
		cpf = user.getCpf();
		birthDate = user.getBirthDate();
		
		return this;
	}
}
