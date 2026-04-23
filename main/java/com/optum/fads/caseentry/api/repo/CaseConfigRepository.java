package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.optum.fads.caseentry.api.domain.CaseConfig;

@Repository
public interface CaseConfigRepository extends JpaRepository<CaseConfig, String>{
	
	@Query("select e.optionValue from CaseConfig e where e.optionCode = :optionCode")
	public String getOptionValue(@Param("optionCode") String optionCode);
	
}
