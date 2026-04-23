/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.CaLuFullscaleSectionT;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaLuFullscaleSectionRepository extends JpaRepository<CaLuFullscaleSectionT, String>, JpaSpecificationExecutor<CaLuFullscaleSectionT> {

}
