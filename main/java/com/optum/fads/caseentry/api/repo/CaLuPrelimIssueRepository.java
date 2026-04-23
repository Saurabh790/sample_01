/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.CaLuPrelimIssueT;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaLuPrelimIssueRepository extends JpaRepository<CaLuPrelimIssueT, String>, JpaSpecificationExecutor<CaLuPrelimIssueT> {

}
