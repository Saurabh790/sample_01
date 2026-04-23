/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.CaLuValuesT;
import com.optum.fads.caseentry.api.domain.CaLuValuesTPK;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaLuValuesRepository extends JpaRepository<CaLuValuesT, CaLuValuesTPK> , JpaSpecificationExecutor<CaLuValuesT>{

}
