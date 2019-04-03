package it.istat.proxy.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.istat.proxy.model.ProvinceJson;
import it.istat.proxy.model.Response;
import it.istat.proxy.storage.ComuniRepositoryI;
import it.istat.proxy.utils.CalcDuration;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ProvinceController {
	
	@Autowired
	ComuniRepositoryI comunario;
	
	/**
	 * Restituisce la lista delle province
	 * @return Set<String> 
	 */
	@RequestMapping(value="/province", method = RequestMethod.GET)

	public Response elencoProvince() {
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ProvinceJson(comunario.listaProvince());
		log.info("/provincie : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}
	
	

}
