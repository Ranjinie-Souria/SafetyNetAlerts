package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
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
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.repository.IFirestationRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	private MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	IFirestationRepository firestationRepository;
	
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
    public void testGetUnknownFirestations() throws Exception {
    	mockMvc.perform(get("/firestations/").param("station", "-5"))
    	.andDo(print())
        .andExpect(status().isOk());
    	List<Firestation> f = firestationRepository.findByStation(-5);
    	List<Firestation> nullFire = new ArrayList<Firestation>();
    	Assertions.assertEquals(nullFire,f);
    }
    
    @Test
    public void testGetFirestationsByAddress() throws Exception {
    	mockMvc.perform(get("/firestations/").param("address", "1509 Culver St"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].station", is(3)));
    }
    
    @Test
    public void getFirestationsByIdTest() throws Exception {
    	mockMvc.perform(get("/firestation/id/2").param("address", "1509 Culver St"))
    	.andDo(print())
        .andExpect(status().isOk());
    	List<Firestation> firestation = firestationRepository.findByStation(2);
    	Assertions.assertEquals(3,firestation.size());    	
    }
    
    @Test
    public void createFirestation() throws Exception {
    	
        Firestation anObject = new Firestation("address",60);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);

    	mockMvc.perform(post("/firestation").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    	
    	List<Firestation> firestation = firestationRepository.findByStation(60);
    	Assertions.assertEquals(1,firestation.size());
    }
    

    
    @Nested
    class updateFirestationTest{
    	
    	@AfterEach
    	public void cleanUpEach() throws Exception{
    		Firestation firestation = firestationRepository.findByStationAddress("1509 Culver St");
    		firestation.setStation(3);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(firestation);
            
        	mockMvc.perform(put("/firestation/1509 Culver St").contentType(APPLICATION_JSON_UTF8)
        	        .content(requestJson));
    	}
    	
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
        	
        	Firestation firestation = firestationRepository.findByStationAddress("1509 Culver St");
        	Assertions.assertEquals(5,firestation.getStation());
        }
        
        @Test
        public void updateIdFirestation() throws Exception {
        	
        	mockMvc.perform(get("/firestation/1509 Culver St"))
        	.andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("station", is(3)));
        	
            Firestation anObject = new Firestation("1509 Culver St",5);
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson=ow.writeValueAsString(anObject);
            
    	    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
    	    parameters.add("stationNumber", "5");
    		parameters.add("address", "1509 Culver St");
    		
        	mockMvc.perform(put("/firestation/").params(parameters).contentType(APPLICATION_JSON_UTF8)
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
