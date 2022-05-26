package com.safetynet.alerts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.MedicalRecord;
import com.safetynet.alerts.service.MedicalRecordService;

@RestController
public class MedicalRecordController {

	private static final Logger logger = LoggerFactory.getLogger(MedicalRecordController.class);

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	/**
	 * Create - Add a new medical Record
	 * @param medicalRecord An object medical Record
	 * @return The medical record object saved
	 */
	@PostMapping("/medicalRecord")
	public MedicalRecord createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
		logger.info("Creating Medical Record");
		medicalRecordService.saveMedicalRecord(medicalRecord);
		return medicalRecord;
	}
	
	
	/**
	 * Read - Get one medical Record 
	 * @param name The name of the person's medical record
	 * @return A medical Record object filled
	 */
	@GetMapping("/medicalRecord/{name}")
	public MedicalRecord getMedicalRecord(@PathVariable("name") String name) {
		logger.info("Getting Medical Record with name "+name);
		MedicalRecord medicalRecord = medicalRecordService.getMedicalRecord(name);
		if(!(medicalRecord == null)) {
			logger.info("Medical Record found");
			return medicalRecord;
		} else {
			logger.info("Medical Record not found");
			return null;
		}
	}
	
	/**
	 * Read - Get all Medical Records
	 * @return - An Iterable object of Medical Records filled
	 */
	@GetMapping("/medicalRecords")
	public List<MedicalRecord> getMedicalRecord() {
		logger.info("Getting all Medical Records");
		return medicalRecordService.getMedicalRecords();
	}
	
	/**
	 * Update - Update an existing Medical Record
	 * @param name - The name of the person's Medical Record to update
	 * @param medicalRecord - The Medical Record object updated
	 * @return - the updated record
	 */
	@PutMapping("/medicalRecord/{name}")
	public MedicalRecord updateMedicalRecord(@PathVariable("name") String name, @RequestBody MedicalRecord medicalRecord) {
		MedicalRecord mR = medicalRecordService.getMedicalRecord(name);
		logger.info("Updating Medical Record "+name);
		if(!(mR == null)) {
			MedicalRecord currentMedicalRecord = mR;
			
			String birthdate = medicalRecord.getBirthdate();
			List<String> allergies = medicalRecord.getAllergies();
			List<String> medications = medicalRecord.getMedications();
			if(birthdate != null) {
				currentMedicalRecord.setBirthdate(birthdate);
			}
			if(allergies != null) {
				currentMedicalRecord.setAllergies(allergies);
			}
			if(medications != null) {
				currentMedicalRecord.setMedications(medications);
			}

			medicalRecordService.saveMedicalRecord(currentMedicalRecord);
			return currentMedicalRecord;
		} else {
			return null;
		}
	}
	
	
	/**
	 * Delete - Delete a Medical Record
	 * @param name - The name of the person's Medical Record to delete
	 */
	@DeleteMapping("/medicalRecord/{name}")
	public void deletePerson(@PathVariable("name") final String name) {
		logger.info("Deleting Medical Record "+name);
		medicalRecordService.deleteMedicalRecord(name);
	}


}
