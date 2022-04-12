package com.safetynet.alerts.repository;

import java.io.IOException;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;

@Repository
public interface PersonRepository{
	
	public static JsonNode getPersons() throws IOException {
		JSONExtracter jsonFile = new JSONExtracter();
	    JsonNode jsonPersons = jsonFile.getPersons();
		return jsonPersons;
	}
    
    
}
