/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.optum.fads.caseentry.api.domain.SeUsrGrp;
/**
 * @author awagh
 *
 */
public interface SeUsrGrpRepository extends JpaRepository<SeUsrGrp,String> , JpaSpecificationExecutor<SeUsrGrp> {

}
