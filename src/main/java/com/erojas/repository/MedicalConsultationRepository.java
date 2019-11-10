package com.erojas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erojas.model.MedicalConsultation;
@Repository
public interface MedicalConsultationRepository extends CrudRepository<MedicalConsultation, Long>{

	@Query("select max(m.id)+1 from MedicalConsultation m")
	public Long generateId();
}
