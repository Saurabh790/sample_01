package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * CaInvstPrAssignTId entity. 
 *  @author Shyam Biry
 */
@Embeddable

public class CaInvstPrAssignTPK implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CASE_ID", nullable = false, insertable = false, updatable = false, unique = true, precision = 7)
	private Integer caseId;
	@Column(name = "ASSIGN_ID", nullable = false, unique = true, precision = 3)
	private Integer assignId;

}