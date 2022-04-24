package com.safetynet.alerts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.safetynet.alerts.service.PersonService;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
    @MockBean
    private PersonService personService;
	
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
        .andExpect(jsonPath("$[0].firstName", is("john")));
    }

}
