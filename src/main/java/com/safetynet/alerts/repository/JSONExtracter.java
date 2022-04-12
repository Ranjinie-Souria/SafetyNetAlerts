package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONExtracter {
	
	public JsonNode getFullJson() throws IOException {
		// Creating an Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		// Getting our file's path
		String jsonPath = "./src/main/resources/data.json";
		File jsonFile = new File(jsonPath);
		// Getting all the contents of the json file
		String content = Files.readString(jsonFile.toPath(), StandardCharsets.US_ASCII);
	    JsonFactory factory = mapper.getFactory();
	    JsonParser parser = factory.createParser(content);
	    JsonNode fullJsonFile = mapper.readTree(parser);
		return fullJsonFile;
	}
	
	public JsonNode getPersons() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("persons");
	    
	    for (JsonNode person : jsonPersons) {
            System.out.println(person);
        }
	    
		return jsonPersons;
	}
	
	public JsonNode getFireStations() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("firestations");
		return jsonPersons;
	}
	
	public JsonNode getMedicalRecords() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("medicalrecords");
		return jsonPersons;
	}

}
