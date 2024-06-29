package com.xantrix.webapp.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * Rappresenta un utente nel sistema.
 * Questa classe è mappata alla collezione "utenti" in MongoDB.
 */
@Document(collection = "utenti")
@Data
public class Utenti {

	/**
	 * Identificatore univoco dell'utente.
	 */
	@Id
	private String id;

	/**
	 * User ID dell'utente. Deve essere unico nel sistema.
	 */
	@Indexed(unique = true)
	@Size(min = 5, max = 80, message = "{Size.Utenti.userId.Validation}")
	@NotNull(message = "{NotNull.Articoli.userId.Validation}")
	private String userId;

	/**
	 * Password dell'utente.
	 */
	@Size(min = 8, max = 80, message = "{Size.Utenti.password.Validation}")
	private String password;

	/**
	 * Indica se l'account dell'utente è attivo.
	 */
	private String attivo = "Si";

	/**
	 * Lista dei ruoli assegnati all'utente.
	 */
	@NotNull(message = "{NotNull.Utenti.ruoli.Validation}")
	private List<String> ruoli;
}
