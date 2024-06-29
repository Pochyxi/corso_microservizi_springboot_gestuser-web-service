package com.xantrix.webapp.exception;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Gestore centralizzato delle eccezioni per l'applicazione REST.
 * Questa classe cattura eccezioni specifiche e le converte in risposte HTTP appropriate.
 */
@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Gestisce le eccezioni di tipo NotFoundException.
	 *
	 * @param ex L'eccezione catturata.
	 * @return ResponseEntity contenente ErrorResponse con stato HTTP 404.
	 */
	@ExceptionHandler(NotFoundException.class)
	public final ResponseEntity<ErrorResponse> exceptionNotFoundHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();

		errore.setDate(LocalDate.now());
		errore.setCode(HttpStatus.NOT_FOUND.value());
		errore.setMessage(ex.getMessage());

		return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/**
	 * Gestisce le eccezioni di tipo BindingException.
	 *
	 * @param ex L'eccezione catturata.
	 * @return ResponseEntity contenente ErrorResponse con stato HTTP 400.
	 */
	@ExceptionHandler(BindingException.class)
	public ResponseEntity<ErrorResponse> exceptionBindingHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();

		errore.setDate(LocalDate.now());
		errore.setCode(HttpStatus.BAD_REQUEST.value());
		errore.setMessage(ex.getMessage());

		return new ResponseEntity<>(errore, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	/**
	 * Gestisce le eccezioni di tipo DuplicateException.
	 *
	 * @param ex L'eccezione catturata.
	 * @return ResponseEntity contenente ErrorResponse con stato HTTP 406.
	 */
	@ExceptionHandler(DuplicateException.class)
	public ResponseEntity<ErrorResponse> exceptionDeplicateRecordHandler(Exception ex) {
		ErrorResponse errore = new ErrorResponse();

		errore.setDate(LocalDate.now());
		errore.setCode(HttpStatus.NOT_ACCEPTABLE.value());
		errore.setMessage(ex.getMessage());

		return new ResponseEntity<>(errore, HttpStatus.NOT_ACCEPTABLE);
	}
}
