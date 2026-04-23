package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * SeCaseGrpNodeAccessPK entity.  @author Anil Wagh
 */
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class SeCaseGrpNodeAccessPK implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CASE_GRP_ID", nullable = false, precision = 0)
	private Integer caseGrpId;
	
	@Column(name = "CASE_NODE_ID", nullable = false, length = 5)
	private String caseNodeId;
	
}