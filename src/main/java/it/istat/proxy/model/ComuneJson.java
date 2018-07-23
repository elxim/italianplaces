package it.istat.proxy.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class ComuneJson extends Response {
	

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.EXISTING_PROPERTY, property = "@class")
	private Comune comune;

	@JsonCreator
	public ComuneJson(@JsonProperty("comune") Comune comune) {
		this.comune = comune;

	}
	
}
