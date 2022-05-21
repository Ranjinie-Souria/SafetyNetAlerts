package com.safetynet.alerts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IMedicalRecordRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

	@Autowired
    private MockMvc mockMvc;
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	IMedicalRecordRepository medicalRecordRepository;
	
	@Test
	public void testGetAllMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalRecords"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
    @Test
    public void testGetOneMedicalRecord() throws Exception {
    	mockMvc.perform(get("/medicalRecord/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.person.firstName", is("John")));
    	
    	Person personExpected = new Person("John","Boyd","1509 Culver St","Culver","97451","8418746512","jaboyd@email.com");
    	List<String> medications = new ArrayList<String>();
    	medications.add("aznol:350mg"); medications.add("hydrapermazol:100mg");
    	List<String> allergies = new ArrayList<String>();
    	allergies.add("nillacilan");
    	MedicalRecord mRExpected = new MedicalRecord(personExpected,"03/06/1984",medications, allergies);
    	MedicalRecord p = medicalRecordRepository.findByName("john.boyd");
    	Assertions.assertEquals(mRExpected,p);
    }
	
	@Test
	public void testCreateMedicalRecord() throws Exception {
		
		MedicalRecord p = medicalRecordRepository.findByName("firstname.lastname");
		Assertions.assertEquals(null,p);
		
		List<String> medicine = new ArrayList<String>();
		MedicalRecord anObject = new MedicalRecord(new Person("firstName","lastName","address",
													"city","zip","phone","email"),"18/05/2010",
													medicine,medicine );
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);
        
    	mockMvc.perform(post("/medicalRecord").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    	
    	MedicalRecord pa = medicalRecordRepository.findByName("firstname.lastname");
    	Assertions.assertEquals(anObject,pa);
	}
	
    
    @Test
    public void updateMedicalRecord() throws Exception {
    	
    	mockMvc.perform(get("/medicalRecord/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.birthdate", is("03/06/1984")));
    	
    	List<String> medicine = new ArrayList<String>();
		MedicalRecord anObject = new MedicalRecord(new Person("John","Boyd","1509 Culver St",
													"Culver","97451","8418746512","jaboyd@email.com"),"18/05/2010",
													medicine,medicine );
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(anObject);
        
    	mockMvc.perform(put("/medicalRecord/john.boyd").contentType(APPLICATION_JSON_UTF8)
    	        .content(requestJson))
    			.andDo(print())
    	        .andExpect(status().isOk());
    	
    	mockMvc.perform(get("/medicalRecord/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.birthdate", is("18/05/2010")));
    	
    	MedicalRecord pa = medicalRecordRepository.findByName("john.boyd");
    	Assertions.assertEquals(anObject,pa);
    }
    
    @Test
    public void deleteMedicalRecord() throws Exception {
    	
    	MedicalRecord p = medicalRecordRepository.findByName("john.boyd");
    	Assertions.assertEquals("John",p.getPerson().getFirstName());
    	
    	mockMvc.perform(delete("/medicalRecord/john.boyd"))
    	.andDo(print())
        .andExpect(status().isOk());
    	
    	p = medicalRecordRepository.findByName("john.boyd");
    	Assertions.assertEquals(null,p);
    }
	
}
