package com.safetynet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.repository.PersonRepositoryJSON;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args) throws IOException{
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
