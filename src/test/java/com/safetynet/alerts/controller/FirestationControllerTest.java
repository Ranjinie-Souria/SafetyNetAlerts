package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class FirestationControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
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

}
