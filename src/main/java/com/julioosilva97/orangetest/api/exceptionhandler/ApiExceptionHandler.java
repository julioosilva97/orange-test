package com.julioosilva97.orangetest.api.exceptionhandler;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.julioosilva97.orangetest.model.exception.UniqueException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		var errors = new ArrayList<ApiError.Error>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			String name = error.getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			errors.add(new ApiError.Error(name, message));
		});

		ex.getBindingResult().getGlobalErrors().forEach(error -> {
			errors.add(new ApiError.Error(error.getObjectName(), error.getDefaultMessage()));
		});

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Um ou mais campos inválidos", errors);
		return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
	}

	@ExceptionHandler(UniqueException.class)
	public ResponseEntity<Object> handleUniqueException(UniqueException ex, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());

		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

	}
	
}
