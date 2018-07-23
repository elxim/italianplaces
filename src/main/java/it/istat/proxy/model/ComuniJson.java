package it.istat.proxy.model;

import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;

public class ComuniJson extends Response {


	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = As.WRAPPER_OBJECT, property = "@class")
	@JsonSubTypes({  
	@Type(value = Comune.class, name = "comune")}) 
	private TreeSet<Comune> comuni;

	@JsonCreator
	public ComuniJson(@JsonProperty("comuni") TreeSet<Comune> comuni) {
		this.comuni = comuni;

	}


}
