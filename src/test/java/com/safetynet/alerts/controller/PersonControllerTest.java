package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.Charset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.alerts.model.Person;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	
	@Test
	public void testGetAllPersons() throws Exception {
		mockMvc.perform(get("/persons"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
    @Test
    public void testGetOnePerson() throws Exception {
    	mockMvc.perform(get("/person/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("firstName", is("John")));
    }
    
    @Test
    public void createPerson() throws Exception {
    	
        Person anObject = new Person("firstName","lastName","address","city","zip","phone","email");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);
        
    	mockMvc.perform(post("/person").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    }
    
    @Test
    public void updatePerson() throws Exception {
    	
    	mockMvc.perform(get("/person/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("address", is("1509 Culver St")));
    	
        Person anObject = new Person("John","Boyd","address","city","zip","phone","email");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);
        
    	mockMvc.perform(put("/person/john.boyd").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    	
    	mockMvc.perform(get("/person/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("address", is("address")));
    }
    
    @Test
    public void deletePerson() throws Exception {
    	mockMvc.perform(delete("/person/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk());
    }
    
	@Test
	public void testGetPersonsForFirestation() throws Exception {
		mockMvc.perform(get("/communityEmail").param("city","Culver"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
	@Test
	public void testGetAllChildFromAddress() throws Exception {
		mockMvc.perform(get("/childAlert").param("address","1509 Culver St"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
	@Test
	public void testGetPersonsInfo() throws Exception {
	    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
	    parameters.add("firstName", "John");
		parameters.add("lastName", "Boyd");
		mockMvc.perform(get("/personInfo").params(parameters))
		.andDo(print())
        .andExpect(status().isOk());
	}
    
    

}
