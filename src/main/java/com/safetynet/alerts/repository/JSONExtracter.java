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
	
	public List<Person> getPersons() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("persons");
	    List<Person> listPersons = new ArrayList<Person>();
	    
	    for (JsonNode person : jsonPersons) {
	    	
            System.out.println(person.get("firstName"));
        }
	    
	    //listPersons.add(new Person("Ran","V", "adresse", "paris", 10, "06", "machin@truc"));
	    //listPersons.add(new Person("Ran2","V2", "adresse2", "paris2", 102, "062", "machin2@truc"));
	    
		return listPersons;
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
