package com.safetynet.alerts.repository;

import java.util.HashMap;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {
	
	public HashMap<String, MedicalRecord> getMedicalRecords();
	
	public MedicalRecord findByName(String name);	

	public HashMap<String, MedicalRecord> findAll();

	public void deleteByName(String name);

	public void save(MedicalRecord medicalRecord);

}
