package com.optum.fads.caseentry.api.repo;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.optum.fads.caseentry.api.domain.SeCaseGrpNodeAccess;
@Transactional
@Repository
public interface SeCaseGrpNodeAccessRepository extends JpaRepository<SeCaseGrpNodeAccess, String>, JpaSpecificationExecutor<SeCaseGrpNodeAccess> {

}