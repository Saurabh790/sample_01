package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Entity provides the base persistence definition of the
 * SeCaseGrp entity.  @author Anil Wagh
 */
@Getter
@Setter
@Entity
@Table(name="SE_CASE_GRP")
public class SeCaseGrp implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CASE_GRP_ID", unique = true, nullable = false, precision = 5, scale = 0)
	private Integer caseGrpId;
	
	@Column(name = "CASE_GRP_NAME", length = 40)
	private String caseGrpName;

}