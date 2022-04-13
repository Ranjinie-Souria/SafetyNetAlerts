package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;
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
	
	public HashMap<String, Person> getPersons() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("persons");
	    HashMap<String, Person> listPersons = new HashMap<String,Person>();
	    
	    for (JsonNode person : jsonPersons) {
	    	String firstName = person.get("firstName").toPrettyString().toLowerCase();
	    	String lastName = person.get("lastName").toPrettyString().toLowerCase();
	    	String keyName = firstName.replaceAll("[^a-zA-Z0-9]", "")+"."
	    					+lastName.replaceAll("[^a-zA-Z0-9]", "");
	    	listPersons.put(keyName,
	    			new Person(person.get("firstName").toPrettyString(),
	    					person.get("lastName").toPrettyString(),
	    					person.get("address").toPrettyString(),
	    					person.get("city").toPrettyString(),
	    					person.get("zip").toPrettyString(),
	    					person.get("phone").toPrettyString(),
	    					person.get("email").toPrettyString()
	    					));
        }
		return listPersons;
	}
	
	public List<Firestation> getFirestations() throws IOException {
	    JsonNode jsonFirestations = getFullJson().get("firestations");
	    List<Firestation> firestations = new ArrayList<Firestation>();
	    
	    for(JsonNode firestation : jsonFirestations) {
	    	firestations.add(new Firestation(firestation.get("address").toPrettyString(),
	    									firestation.get("station").toPrettyString()));
	    }
	    
		return firestations;
	}
	
	public JsonNode getMedicalRecords() throws IOException {
	    JsonNode jsonPersons = getFullJson().get("medicalrecords");
		return jsonPersons;
	}

}
