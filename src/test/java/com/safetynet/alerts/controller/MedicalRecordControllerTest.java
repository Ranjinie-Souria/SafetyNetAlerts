package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.safetynet.alerts.service.MedicalRecordService;

@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

	@Autowired
    private MockMvc mockMvc;
	
    @MockBean
    private MedicalRecordService medicalRecordService;
	
	@Test
	public void testGetAllMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalRecords"))
		.andDo(print())
        .andExpect(status().isOk());
	}
	
}
