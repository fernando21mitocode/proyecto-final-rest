package com.erojas.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.erojas.model.DetailConsultation;
@Repository
public interface DetailConsultationRepository extends CrudRepository<DetailConsultation, Long>{

	@Query("select max(d.id)+1 from DetailConsultation d")
	public Long generateId();
}
