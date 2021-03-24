package com.julioosilva97.orangetest.api.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import com.sun.istack.NotNull;

public class VaccineInput implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String name;

	@NotNull
	@PastOrPresent
	private LocalDate date;

	@NotNull
	private String userEmail;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	public VaccineInput(@NotEmpty String name, LocalDate date, String userEmail) {
		this.name = name;
		this.date = date;
		this.userEmail = userEmail;
	}

	public VaccineInput() {
	}
}
