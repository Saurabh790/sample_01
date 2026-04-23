/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.CaLuCaseSourceT;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaLuCaseSourceRepository extends JpaRepository<CaLuCaseSourceT, String>, JpaSpecificationExecutor<CaLuCaseSourceT> {

}
