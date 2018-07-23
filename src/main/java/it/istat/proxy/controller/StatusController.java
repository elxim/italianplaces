package it.istat.proxy.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.istat.proxy.ComuniProperties;
import it.istat.proxy.storage.Comuni;

@RestController
public class StatusController {

	@Autowired
	Comuni comunario;
	
	@Autowired
	ComuniProperties props;
	
	@RequestMapping(value="/status", method = RequestMethod.GET)
	public Map<String,String> counter() {
		Map<String,String> result = new HashMap<String,String>();
		
		result.put("comuni", (null!=comunario)?"presenti":"assenti");
		result.put("fonte", (comunario.getComuniLoaded()?props.getSourceUrl():props.getLocalPath().concat(props.getXlsname())));
		result.put("numero",(null!=comunario.numeroComuni())?comunario.numeroComuni():"0");
		result.put("istatSourceName", (null!=comunario.istatSourceName())?comunario.istatSourceName():"?");
		result.put("ultimoAggiornamento",(null!=comunario.lastUpdate())?comunario.lastUpdate():"?");
		result.put("ultimoControllo",(null!=comunario.lastCheck())?comunario.lastUpdate():"?");
		result.put("versione",(null!=props.getVersionNumber())?props.getVersionNumber():"?");
		result.put("user.home", System.getProperty("user.home"));
		return result;
	}

}
