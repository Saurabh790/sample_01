/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.CaLuPrmNodeT;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaLuPrmNodesRepository extends JpaRepository<CaLuPrmNodeT, String> {

}
