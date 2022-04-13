package com.safetynet.alerts;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.repository.IPersonRepository;
import com.safetynet.alerts.repository.PersonRepositoryJSON;

@SpringBootApplication
public class SafetyNetAlertsApplication {

	public static void main(String[] args){
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
		try {
			IPersonRepository pr = new PersonRepositoryJSON();
			System.out.println(pr.findAll());
			pr.save(new Person("Nouveau","Personne", "newadress", "ville", "90", "065", "truc@truc"));
			System.out.println(pr.findAll());
			System.out.println(pr.findById(0));
			System.out.println(pr.findById(1));
			pr.deleteById(1);
			System.out.println(pr.findAll());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
