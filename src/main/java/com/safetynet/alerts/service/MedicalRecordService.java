package com.safetynet.alerts.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordService {
	
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;
	
	public MedicalRecord getFirestation(String name) {
		return medicalRecordRepository.findByName(name);
	}
	
	public HashMap<String, MedicalRecord> getFirestations() {
		return medicalRecordRepository.findAll();
	}
	
	public void deleteMedicalRecord(String name) {
		medicalRecordRepository.deleteByName(name);
	}
	
	public void saveMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.save(medicalRecord);
	}
	

}
