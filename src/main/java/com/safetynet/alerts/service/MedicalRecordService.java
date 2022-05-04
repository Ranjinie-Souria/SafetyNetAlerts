package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.repository.IMedicalRecordRepository;

@Service
public class MedicalRecordService {
	
	@Autowired
	private IMedicalRecordRepository medicalRecordRepository;
	
	public MedicalRecord getMedicalRecord(String name) {
		return medicalRecordRepository.findByName(name);
	}
	
	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecordRepository.findAll();
	}
	
	public void deleteMedicalRecord(String name) {
		medicalRecordRepository.deleteByName(name);
	}
	
	public void saveMedicalRecord(MedicalRecord medicalRecord) {
		medicalRecordRepository.save(medicalRecord);
	}
	

}
