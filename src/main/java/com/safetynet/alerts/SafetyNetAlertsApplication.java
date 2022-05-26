package com.safetynet.alerts;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertsApplication {
	//private static final Logger logger = LogManager.getLogger(SafetyNetAlertsApplication.class);
	private static final Logger logger = LoggerFactory.getLogger(SafetyNetAlertsApplication.class);
	public static void main(String[] args) throws IOException{
		logger.info("Initializing SafetyNet Alerts Application");
		SpringApplication.run(SafetyNetAlertsApplication.class, args);
	}

}
