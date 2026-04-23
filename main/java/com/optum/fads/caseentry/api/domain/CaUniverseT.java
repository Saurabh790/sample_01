package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import com.optum.fads.caseentry.api.common.constants.CaseEntryConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The persistent class for the CA_UNIVERSE_BATCH_T database table. 
 * 
 */

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "CA_UNIVERSE_T")
public class CaUniverseT implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CaUniverseTPK id;
	
	@Column(name = "CT_LOAD_STATUS_CD", length = 3)
	@Builder.Default
	private String ctLoadStatusCd = CaseEntryConstants.BATCH_COMPLETED;
	
	@Column(name = "CT_PROCESS_CD", length = 3)
	@Builder.Default
	private String ctProcessCd = CaseEntryConstants.BATCH_IMPORTED;
	
	@Column(name = "HDR_CLM_TYPE_CD", length = 2)
	private String hdrClmTypeCd;
	
	@Column(name = "CASE_ID", unique = true, nullable = false, precision = 7, scale = 0)
	private Integer caseId;
		
	//bi-directional one-to-one association to UiUserBase
	@OneToOne
	@JoinColumn(name="UI_SYSTEM_ID")
	private UiUserBase uiUserBase;
	
	@Column(name = "CASE_TYPE_CD", length = 3)
	private String caseTypeCd;
	
	@Column(name = "PROV_ROLE", length = 30)
	private String provRole;
			
	@Column(name = "CA_PRM_NODE_CD")
	private String caPrmNodeCd;
	
	@Column(name = "CA_YEAR_ID", precision = 4)
	private Integer caYearId;
	
	@Column(name = "CA_SEQUENCE_ID", precision = 7)
	private Integer caSequenceId;
	
	@Column(name = "PROTECT_STATUS", length = 1)
	private String projectStatus;
	
	@Column(name = "INVST_TYPE_CD", length = 3)
	private String invstTypeCd;
	
	@Column(name = "CASE_SOURCE_CD", length = 3)
	private String caseSourceCd;
	
	@Column(name = "CASE_STATUS_CD", length = 3)
	private String caseStatusCd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CASE_STATUS_DT")
	private Date caseStatusDt;
	
	@Column(name = "ISSUE_CD", length = 3)
	private String issueCd;
	
	@Column(name = "ASSIGN_USR", length = 25)
	private String assignUsr;
	
	@Column(name = "ASSIGN_SECTION_CD", length = 3)
	private String assignSectionCd;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ASSIGN_DATE")
	private Date assignDate;
	
	@Column(name = "PARTICIPANT_ID", length = 25)
	private String participantId;
	
	@Column(name = "CLM_SEQ_NUM", precision = 7)
	private Integer clmSeqNum;
	
}
