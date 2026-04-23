package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaLuPrelimIssueT entity. @author awagh
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_LU_PRELIM_ISSUE_T")
public class CaLuPrelimIssueT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ISSUE_CD", unique = true, nullable = false, length = 3)
	private String issueCd;
	
	@Column(name = "ISSUE_DESC", length = 50)
	private String issueDesc;
	
	@Column(name = "ISSUE_VIEW", length = 1)
	private String issueView;
	
	@Column(name = "ISSUE_SEQ", precision = 5)
	private Integer issueSeq;
	
	@Column(name = "ISSUE_FILTER", length = 5)
	private String issueFilter;
	
	@Column(name = "ISSUE_SCORE", length = 5)
	private String issueScore;
	
	@Column(name = "ISSUE_TYPE", length = 15)
	private String issueType;

}
