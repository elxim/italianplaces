package it.istat.proxy.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.istat.proxy.model.ListaCittaMetropolitaneJson;
import it.istat.proxy.model.Response;
import it.istat.proxy.storage.ComuniRepositoryI;
import it.istat.proxy.utils.CalcDuration;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class CittaMetropolitaneController {

	
	@Autowired
	ComuniRepositoryI comunario;

	/**
	 * Restituisce l'elenco delle città metropoliane
	 * @return
	 */
	@RequestMapping(value = "/cittaMetropolitane", method = RequestMethod.GET)
	public Response elencoCittaMetropolitane() {
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ListaCittaMetropolitaneJson(comunario.listaCittàMetropolitane());
			
		
		log.info("/cittaMetropolitane : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}
	
}
