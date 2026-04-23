package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *  SeCaseGrpNodeAccess entity.  @author Anil Wagh
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "SE_CASE_GRP_NODE_ACCESS")
public class SeCaseGrpNodeAccess implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private SeCaseGrpNodeAccessPK id;
	
	@Column(name = "CASE_NODE_ACCESS_TYPE", nullable = false, length = 1)
	private String caseNodeAccessType;
	
	@Column(name = "ANY_RESTRICT_APPLY", nullable = false, length = 1)
	private String anyRestrictApply;
	
	@Column(name = "CAN_DELETE_CASES", nullable = false, length = 1)
	private String canDeleteCases;

}