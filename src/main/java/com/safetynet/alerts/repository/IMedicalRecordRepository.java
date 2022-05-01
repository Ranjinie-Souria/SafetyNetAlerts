package com.safetynet.alerts.repository;

import java.util.HashMap;

import com.safetynet.alerts.model.MedicalRecord;

public interface IMedicalRecordRepository {

	
	/**
	 * @param name - the name of the medical record to find
	 * @return the medical record to find
	 */
	public MedicalRecord findByName(String name);	

	/**
	 * @return all the medical records with the names as keys
	 */
	public HashMap<String, MedicalRecord> findAll();

	/**
	 * @param name - the name of the medical record to delete
	 */
	public void deleteByName(String name);

	/**
	 * @param medicalRecord - the name of the medical record to save
	 */
	public void save(MedicalRecord medicalRecord);

}
