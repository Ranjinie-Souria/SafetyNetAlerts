package com.safetynet.alerts.model;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Component
@RequiredArgsConstructor
public class Firestation {
	
	public Firestation(String address, int station) {
		this.address = address;
		this.station = station;
	}
	
	private String address;
	private int station;

}
