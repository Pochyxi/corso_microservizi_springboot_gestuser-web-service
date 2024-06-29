package com.xantrix.webapp.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Eccezione personalizzata per gestire errori di risorse non trovate.
 * Questa eccezione è tipicamente utilizzata quando un'operazione di ricerca
 * o recupero non riesce a trovare l'elemento richiesto.
 */
@Getter
@Setter
public class NotFoundException extends Exception {

	/**
	 * Numero di versione serializzata per garantire la compatibilità durante la serializzazione.
	 */
	private static final long serialVersionUID = -8729169303699924451L;

	/**
	 * Messaggio predefinito dell'eccezione.
	 * Può essere sovrascritto dal costruttore con parametro.
	 */
	private String messaggio = "Elemento Ricercato Non Trovato!";

	/**
	 * Costruttore di default.
	 * Utilizza il messaggio predefinito.
	 */
	public NotFoundException() {
		super();
	}

	/**
	 * Costruttore che accetta un messaggio personalizzato.
	 *
	 * @param messaggio Il messaggio dettagliato dell'eccezione.
	 */
	public NotFoundException(String messaggio) {
		super(messaggio);
		this.messaggio = messaggio;
	}
}
