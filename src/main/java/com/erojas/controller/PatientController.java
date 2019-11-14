package com.erojas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.erojas.model.Patient;
import com.erojas.service.PatientService;

@RestController
public class PatientController {
	@Autowired
	private PatientService PatientService;

	// -------------------Retrieve All Patients-------------------------------------

	@GetMapping(path = "/patients")
	private ResponseEntity<List<Patient>> findAll() {
		List<Patient> listPatient = PatientService.findAll();

		if (listPatient.isEmpty()) {
			return new ResponseEntity<List<Patient>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Patient>>(listPatient, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Patient-------------------------------------
	@GetMapping(path = "/patients/{id}")
	private ResponseEntity<Patient> findById(@PathVariable(name = "id") Long id) {

		if (!PatientService.isUserExist(id)) {
			return new ResponseEntity<Patient>(HttpStatus.NO_CONTENT);
		}

		Patient currentPatient = PatientService.findById(id).get();
		return new ResponseEntity<Patient>(currentPatient, HttpStatus.OK);
	}

	// -------------------Create a
	// Patient--------------------------------------------

	@PostMapping(path = "/patients")
	private ResponseEntity<Void> save(@RequestBody Patient Patient, UriComponentsBuilder uriComponentsBuilder) {

		if (PatientService.isUserExist(Patient.getId())) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		PatientService.save(Patient);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	// -------------------Update a
	// Patient--------------------------------------------
	@PutMapping(path = "/patients/{id}")
	private ResponseEntity<Void> updatePatient(@PathVariable(name = "id") Long id, @RequestBody Patient Patient,
			UriComponentsBuilder uriComponentsBuilder) {

		if (!PatientService.isUserExist(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Patient currentPatient = new Patient();
		currentPatient.setId(Patient.getId());
		currentPatient.setFirstName(Patient.getFirstName());
		currentPatient.setLastName(Patient.getLastName());
		currentPatient.setDni(Patient.getDni());
		

		PatientService.update(currentPatient);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	
}
