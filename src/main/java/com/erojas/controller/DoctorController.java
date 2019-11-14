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

import com.erojas.model.Doctor;
import com.erojas.service.DoctorService;

@RestController
public class DoctorController {
	@Autowired
	private DoctorService doctorService;

	// -------------------Retrieve All Doctors-------------------------------------

	@GetMapping(path = "/doctors")
	private ResponseEntity<List<Doctor>> findAll() {
		List<Doctor> listDoctor = doctorService.findAll();

		if (listDoctor.isEmpty()) {
			return new ResponseEntity<List<Doctor>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Doctor>>(listDoctor, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Doctor-------------------------------------
	@GetMapping(path = "/doctors/{id}")
	private ResponseEntity<Doctor> findById(@PathVariable(name = "id") Long id) {

		if (!doctorService.isUserExist(id)) {
			return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
		}

		Doctor currentDoctor = doctorService.findById(id).get();
		return new ResponseEntity<Doctor>(currentDoctor, HttpStatus.OK);
	}

	// -------------------Create a
	// Doctor--------------------------------------------

	@PostMapping(path = "/doctors")
	private ResponseEntity<Void> save(@RequestBody Doctor Doctor, UriComponentsBuilder uriComponentsBuilder) {

		if (doctorService.isUserExist(Doctor.getId())) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		doctorService.save(Doctor);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	// -------------------Update a
	// Doctor--------------------------------------------
	@PutMapping(path = "/doctors/{id}")
	private ResponseEntity<Void> updateDoctor(@PathVariable(name = "id") Long id, @RequestBody Doctor doctor,
			UriComponentsBuilder uriComponentsBuilder) {

		if (!doctorService.isUserExist(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Doctor currentDoctor = new Doctor();
		currentDoctor.setId(doctor.getId());
		currentDoctor.setFirstName(doctor.getFirstName());
		currentDoctor.setLastName(doctor.getLastName());
		currentDoctor.setDni(doctor.getDni());
		currentDoctor.setCmp(doctor.getCmp());

		doctorService.update(currentDoctor);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}



}
