package com.safetynet.alerts.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

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
	public MedicalRecord findByName(String name) {
		return jsonMedicalRecord.get(name);
	}

	@Override
	public List<MedicalRecord> findAll() {
		List<MedicalRecord> medicalRecords = new ArrayList<MedicalRecord>();
		for(Entry<String, MedicalRecord> entry : jsonMedicalRecord.entrySet()) {
			medicalRecords.add(entry.getValue());			
		}
		return medicalRecords;
	}

	@Override
	public void deleteByName(String name) {
		jsonMedicalRecord.remove(name);
	}

	@Override
	public void save(MedicalRecord medicalRecord) {
		String keyName = medicalRecord.getPerson().getFirstName()+"."+medicalRecord.getPerson().getLastName();
		keyName = keyName.toLowerCase();
		jsonMedicalRecord.put(keyName,medicalRecord);
	}

}
