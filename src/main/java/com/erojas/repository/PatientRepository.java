package com.erojas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erojas.model.Patient;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{
	@Query("select max(p.id)+1 from Patient p")
	public Long generateId();

	
	

}
