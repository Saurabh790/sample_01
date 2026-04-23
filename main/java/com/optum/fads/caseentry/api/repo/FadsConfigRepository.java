/**
 * 
 */
package com.optum.fads.caseentry.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.optum.fads.caseentry.api.domain.FadsConfigT;
/**
 * @author awagh
 *
 */
public interface FadsConfigRepository extends JpaRepository<FadsConfigT,Long> , JpaSpecificationExecutor<FadsConfigT> {

}
