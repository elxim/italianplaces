package it.istat.proxy.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import it.istat.proxy.model.Comune;
import it.istat.proxy.model.ComuneJson;
import it.istat.proxy.model.ComuniJson;
import it.istat.proxy.model.ListaComuniJson;
import it.istat.proxy.model.Response;
import it.istat.proxy.storage.ComuniRepositoryI;
import it.istat.proxy.utils.CalcDuration;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ComuniController {

	@Autowired
	ComuniRepositoryI comunario;

	/**
	 * Restituisce la lista dei comuni data la regione
	 * 
	 * @param denominazioneRegione
	 * @return
	 */
	@RequestMapping(value = "/comuni/regione/{denominazioneRegione}", method = RequestMethod.GET)
	public Response elencoComuniPerRegione(@PathVariable("denominazioneRegione") String denominazioneRegione) {
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ListaComuniJson(comunario.listaComuniPerRegione(denominazioneRegione));
		log.info("/comuni/regione/{denominazioneRegione} : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}

	/**
	 * Restituisce la lista dei comuni data la provincia
	 * 
	 * @param denominazioneRegione
	 * @return
	 */
	@RequestMapping(value = "/comuni/provincia/{denominazioneProvincia}", method = RequestMethod.GET)
	public Response elencoComuniPerProvincia(@PathVariable("denominazioneProvincia") String denominazioneProvincia) {
		log.info("/comuni/provinica/".concat(denominazioneProvincia));
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ListaComuniJson(comunario.listaComuniPerProvincia(denominazioneProvincia));
		log.info("/cittaMetropolitane : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}

	/**
	 * Restituisce la lista dei comuni data la città metropolitana
	 * 
	 * @param denominazioneRegione
	 * @return
	 */
	@RequestMapping(value = "/comuni/cittaMetropolitana/{denominazioneCittàMetropolitana}", method = RequestMethod.GET)
	public Response elencoComuniPerCittàMetropolitana(
			@PathVariable("denominazioneCittàMetropolitana") String denominazioneCittàMetropolitana) {
		log.info("comuni/cittaMetropolitana/".concat(denominazioneCittàMetropolitana));
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ListaComuniJson(
				comunario.listaComuniPerCittàMetropolitana(denominazioneCittàMetropolitana));
		log.info("/comuni/cittaMetropolitana/{denominazioneCittàMetropolitana} : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}

	/**
	 * Restituisce il dettaglio dei/l comune
	 * 
	 * @param denominazioneComune
	 * @return
	 */
	@RequestMapping(value = {"/comuni/{denominazioneComune}",
				"/comuni/{denominazioneComune}/{altraDenominazione}"}, method = RequestMethod.GET)
	public Response dettaglioComuni(@PathVariable("denominazioneComune") String denominazioneComune,
			@PathVariable("altraDenominazione") @ApiParam(required = false) Optional<String> altraDenominazione) {
		
		/**
		 * @mpizza 20190322 bugfix per i comuni con il carattere /
		 */
		if (altraDenominazione.isPresent())
			denominazioneComune = denominazioneComune.concat("/").concat(altraDenominazione.get());

		log.info("/comuni/".concat(denominazioneComune));
		
		LocalDateTime start = LocalDateTime.now();
		
		Response risposta = new ComuniJson(comunario.getComunibyName(denominazioneComune));
		
		log.info("/comuni/{denominazioneComune} : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}

	/**
	 * Restituisce il dettaglio di un singolo comune selezionato per id
	 * 
	 * @param idComune
	 * @return
	 */
	@RequestMapping(value = "/comune/{idComune}", method = RequestMethod.GET)
	public Response dettaglioComune(@PathVariable("idComune") String idComune) {
		log.info("/comune/".concat(idComune));
		LocalDateTime start = LocalDateTime.now();
		Response risposta = new ComuneJson(comunario.getComunebyId(idComune));
		log.info("/comune/{idComune} : end in : ".concat(CalcDuration.secondi(start)));
		return risposta;
	}

	/**
	 * Elenco comuni
	 * 
	 * @return
	 */
	@RequestMapping(value = "/comuni", method = RequestMethod.GET)
	public Response elencoComuni() {
		log.info("/comuni");
		LocalDateTime start = LocalDateTime.now();
		Response riposta = new ListaComuniJson(comunario.listaComuni());
		log.info("/comuni: end in : ".concat(CalcDuration.secondi(start)));
		return riposta;
	}

	
	@RequestMapping(value = "/comuniFullList", method = RequestMethod.GET)
	public HashMap<String, Comune> elencoComuniFullList() {
		log.info("/comuniFullList");
		LocalDateTime start = LocalDateTime.now();
		log.info("/comuniFullList : end in : ".concat(CalcDuration.secondi(start)));
		return comunario.getComunario();
	}

}
