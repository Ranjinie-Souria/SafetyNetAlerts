package com.safetynet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.repository.PersonRepositoryJSON;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args){
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		try {
			IPersonRepository pr = new PersonRepositoryJSON();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
