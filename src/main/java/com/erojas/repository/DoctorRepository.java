package com.erojas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erojas.model.Doctor;
@Repository
public interface DoctorRepository extends CrudRepository<Doctor, Long>{

	@Query("select max(d.id)+1 from Doctor d")
	Long generateId();

}