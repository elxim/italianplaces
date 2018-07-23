package it.istat.proxy.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class ListaCittaMetropolitaneJson extends Response {

	
	@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = As.PROPERTY, property = "@class")
	private Set<String> cittaMetropolitane;

	@JsonCreator
	public ListaCittaMetropolitaneJson(@JsonProperty("cittaMetropolitane") Set<String> cittàMetropolitane) {
		this.cittaMetropolitane = cittàMetropolitane;

	}
	
}
