package it.istat.proxy.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class RegioniJson extends Response {
	
	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
	private Set<String> regioni;

	@JsonCreator
	public RegioniJson(@JsonProperty("regioni") Set<String> regioni) {
		this.regioni = regioni;

	}


}
