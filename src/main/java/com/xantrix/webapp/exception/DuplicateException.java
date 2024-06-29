package com.xantrix.webapp.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Eccezione personalizzata per gestire errori di duplicazione.
 * Utilizza le annotazioni Lombok per generare automaticamente i metodi getter e setter.
 */
@Getter
@Setter
public class DuplicateException extends Exception {

	/**
	 * Numero di versione serializzata per garantire la compatibilità durante la serializzazione.
	 */
	private static final long serialVersionUID = -5870522280695461980L;

	/**
	 * Messaggio dettagliato dell'eccezione.
	 */
	private String messaggio;

	/**
	 * Costruttore di default.
	 */
	public DuplicateException() {
		super();
	}

	/**
	 * Costruttore che accetta un messaggio personalizzato.
	 *
	 * @param messaggio Il messaggio dettagliato dell'eccezione.
	 */
	public DuplicateException(String messaggio) {
		super(messaggio);
		this.messaggio = messaggio;
	}
}
