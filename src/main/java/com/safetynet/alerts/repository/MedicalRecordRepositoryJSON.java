package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.MedicalRecord;

@Repository
public class MedicalRecordRepositoryJSON implements IMedicalRecordRepository{
	
	JSONExtracter jsonFile = new JSONExtracter();
	HashMap<String, MedicalRecord> jsonMedicalRecord;
	
	public MedicalRecordRepositoryJSON() throws IOException {
		jsonMedicalRecord = jsonFile.getMedicalRecords();
	}

	@Override
	public HashMap<String, MedicalRecord> getMedicalRecords() {
		return jsonMedicalRecord;
	}

	@Override
	public MedicalRecord findByName(String name) {
		return jsonMedicalRecord.get(name);
	}

	@Override
	public HashMap<String, MedicalRecord> findAll() {
		return jsonMedicalRecord;
	}

	@Override
	public void deleteByName(String name) {
		jsonMedicalRecord.remove(name);
	}

	@Override
	public void save(MedicalRecord medicalRecord) {
		String keyName = medicalRecord.getPerson().getFirstName()+"."
						+medicalRecord.getPerson().getFirstName();
		jsonMedicalRecord.put(keyName,medicalRecord);
	}

}
