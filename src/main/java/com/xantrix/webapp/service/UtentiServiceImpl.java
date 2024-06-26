package com.xantrix.webapp.service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.model.Utenti;
import com.xantrix.webapp.repository.UtentiRepository;
 
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UtentiServiceImpl implements UtentiService
{
	private final UtentiRepository utentiRepository;
	
	@Override
	public List<Utenti> SelTutti()
	{
		return utentiRepository.findAll();
	}

	@Override
	public Utenti SelUser(String UserId)
	{
		return utentiRepository.findByUserId(UserId);
	}


	@Override
	public void Save(Utenti utente)
	{
		utentiRepository.save(utente);
	}

	@Override
	public void Delete(Utenti utente)
	{
		utentiRepository.delete(utente);
	}

}
