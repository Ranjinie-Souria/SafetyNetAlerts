package com.safetynet.alerts.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Firestation {
	
	public Firestation(String address, String station) {
		this.address = address;
		this.station = station;
	}
	
	private String address;
	private String station;

}
