package com.xantrix.webapp.exception;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

/**
 * Rappresenta una risposta di errore standardizzata.
 * Questa classe è utilizzata per incapsulare i dettagli di un errore
 * in un formato coerente per le risposte API.
 */
@Getter
@Setter
public class ErrorResponse {

	/**
	 * La data in cui si è verificato l'errore.
	 */
	private LocalDate date;

	/**
	 * Il codice di stato HTTP associato all'errore.
	 */
	private int code;

	/**
	 * Un messaggio descrittivo dell'errore.
	 */
	private String message;
}
