package it.istat.proxy.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller che fornisce l'elenco degli stati esteri
 * @author mpizza
 *
 */
@RestController
public class StatiEsteriController {
	
	@RequestMapping(value="/statiesteri", method = RequestMethod.GET,  produces={"application/json; charset=ISO-8859-1"})
	public ResponseEntity elencoStatiEsteri() throws IOException {

		ClassPathResource jsonFile = new ClassPathResource("statiesteri.json");

	    return ResponseEntity
	            .ok()
	            .contentLength(jsonFile.contentLength())
	            .contentType(
	                    MediaType.parseMediaType("application/json;charset=ISO-8859-1"))
	            .body(new InputStreamResource(jsonFile.getInputStream(),"ISO-8859-1"));
	
		
		
	}

}
