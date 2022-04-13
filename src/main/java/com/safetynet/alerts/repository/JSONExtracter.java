package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Person;

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
	    List<Person> listPersons = new ArrayList<Person>();
	    
	    for (JsonNode person : jsonPersons) {
	    	
	    	//System.out.println(person);
            //System.out.println(person.get(0).get("firstName"));
            System.out.println(person.get("firstName"));
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
