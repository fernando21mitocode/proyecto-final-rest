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

import com.erojas.model.Specialty;
import com.erojas.service.SpecialtyService;

@RestController
public class SpecialtyController {

	@Autowired
	private SpecialtyService SpecialtyService;

	// -------------------Retrieve All Specialtys-------------------------------------

	@GetMapping(path = "/specialtys")
	private ResponseEntity<List<Specialty>> findAll() {
		List<Specialty> listSpecialty = SpecialtyService.findAll();

		if (listSpecialty.isEmpty()) {
			return new ResponseEntity<List<Specialty>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Specialty>>(listSpecialty, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Specialty-------------------------------------
	@GetMapping(path = "/specialtys/{id}")
	private ResponseEntity<Specialty> findById(@PathVariable(name = "id") Long id) {

		if (!SpecialtyService.isUserExist(id)) {
			return new ResponseEntity<Specialty>(HttpStatus.NO_CONTENT);
		}

		Specialty currentSpecialty = SpecialtyService.findById(id).get();
		return new ResponseEntity<Specialty>(currentSpecialty, HttpStatus.OK);
	}

	// -------------------Create a
	// Specialty--------------------------------------------

	@PostMapping(path = "/specialtys")
	private ResponseEntity<Void> save(@RequestBody Specialty Specialty, UriComponentsBuilder uriComponentsBuilder) {

		if (SpecialtyService.isUserExist(Specialty.getId())) {

			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		SpecialtyService.save(Specialty);

		return new ResponseEntity<Void>(HttpStatus.CREATED);

	}

	// -------------------Update a
	// Specialty--------------------------------------------
	@PutMapping(path = "/specialtys/{id}")
	private ResponseEntity<Void> updateSpecialty(@PathVariable(name = "id") Long id, @RequestBody Specialty Specialty,
			UriComponentsBuilder uriComponentsBuilder) {

		if (!SpecialtyService.isUserExist(id)) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		Specialty currentSpecialty = new Specialty();
		currentSpecialty.setId(Specialty.getId());
		currentSpecialty.setName(Specialty.getName());


		SpecialtyService.update(currentSpecialty);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}


	
}
