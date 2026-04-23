/**
 *
 */
package com.optum.fads.caseentry.api.repo;

import com.optum.fads.caseentry.api.domain.CaUniverseT;
import com.optum.fads.caseentry.api.domain.CaUniverseTPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author awagh
 *
 */

@Transactional
@Repository
public interface CaUniverseRepository extends JpaRepository<CaUniverseT, CaUniverseTPK>, JpaSpecificationExecutor<CaUniverseT> {
//	@Query(value = "SELECT  Max(CT_BATCH_ID+1) FROM CA_UNIVERSE_T", nativeQuery = true)

    @Query(value = " SELECT MAX(CT_BATCH_ID +1) AS CT_BATCH_ID FROM ( " +
                   " SELECT MAX(CT_BATCH_ID) AS CT_BATCH_ID FROM CA_UNIVERSE_T " +
                   " UNION " +
                   " SELECT MAX(CT_BATCH_ID) AS CT_BATCH_ID FROM CA_UNIVERSE_BATCH_T " +
                    ") AS CA_UNIVERSE_MAX_ID", nativeQuery = true)
    Integer getNextCaseBatchId();
}
