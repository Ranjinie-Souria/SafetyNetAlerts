package com.safetynet.alerts.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public interface PersonRepository{
	
	public static void test() throws IOException {
		
		// create Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		String jsonPath = "./src/main/resources/data.json";
		File jsonFile = new File(jsonPath);
		String content = Files.readString(jsonFile.toPath(), StandardCharsets.US_ASCII);
		Object jsonData = mapper.readValue(content, Object.class);
		System.out.println(jsonData);

	}
    
    
}
