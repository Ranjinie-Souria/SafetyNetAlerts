package com.safetynet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.repository.PersonRepository;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args){
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		try {
			PersonRepository.test();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
