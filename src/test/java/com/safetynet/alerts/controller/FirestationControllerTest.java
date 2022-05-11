package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.alerts.model.Firestation;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	private MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Test
	public void testGetAllFirestations() throws Exception {
		mockMvc.perform(get("/firestations"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
    @Test
    public void testGetFirestationsByNumber() throws Exception {
    	mockMvc.perform(get("/firestations/").param("station", "3"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].address", is("1509 Culver St")));
    }
    
    @Test
    public void testGetFirestationsByAddress() throws Exception {
    	mockMvc.perform(get("/firestations/").param("address", "1509 Culver St"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].station", is(3)));
    }
    
    @Test
    public void createFirestation() throws Exception {
    	
        Firestation anObject = new Firestation("address",6);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);

    	mockMvc.perform(post("/firestation").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    }
    

    
    @Nested
    class updateFirestationTest{
    	
        @Test
        public void updateFirestation() throws Exception {
        	
        	mockMvc.perform(get("/firestation/1509 Culver St"))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("station", is(3)));
        	
            Firestation anObject = new Firestation("1509 Culver St",5);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(anObject);
            
        	mockMvc.perform(put("/firestation/1509 Culver St").contentType(APPLICATION_JSON_UTF8)
        	        .content(requestJson))
        			.andDo(print())
        	        .andExpect(status().isOk());
        	
        	mockMvc.perform(get("/firestation/1509 Culver St"))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("station", is(5)));
        }
    	
    }

}
