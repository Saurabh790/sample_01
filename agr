package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import jakarta.persistence.*;

import lombok.*;

/**
 * The persistent class for the CA_UNIVERSE_BATCH_T database table. 
 * 
 */


@EqualsAndHashCode
@NoArgsConstructor
@Data
@ToString
@Entity
@Table(name = "CA_UNIVERSE_BATCH_T")
public class CaUniverseBatchT implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CA_BATCH_ROWSEQ", precision = 14)
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer caBatchRowSeq;

    @Column(name = "CT_BATCH_ID",  nullable = false, precision = 10)
    private Integer ctBatchId;



    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CT_BATCH_DATE")
    private LocalDateTime ctBatchDate;

    @Column(name = "HDR_CLM_TCN", unique = true, nullable = false, length = 17)
    private String hdrClmTcn;

    @Column(name = "LI_NUM", unique = true, nullable = false, length = 4)
    private String liNum;

    @Temporal(TemporalType.DATE)
    @Column(name = "HDR_CLM_PD_DT")
    private Date hdrClmPdDt;
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
	
	@Column(name = "PROTECT_STATUS", length = 1)
	private String projectStatus;
	
	@Column(name = "INVST_TYPE_CD", length = 3)
	private String invstTypeCd;
	
	@Column(name = "CASE_SOURCE_CD", length = 3)
	private String caseSourceCd;
	
	@Column(name = "CASE_STATUS_CD", length = 3)
	private String caseStatusCd;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CASE_STATUS_DT")
	private Date caseStatusDt;
	
	@Column(name = "ISSUE_CD", length = 3)
	private String issueCd;
	
	@Column(name = "ASSIGN_USR", length = 25)
	private String assignUsr;
	
	@Column(name = "ASSIGN_SECTION_CD", length = 3)
	private String assignSectionCd;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ASSIGN_DATE")
	private Date assignDate;
	
	@Column(name = "PARTICIPANT_ID", length = 25)
	private String participantId;

	@Column(name = " LI_DRUG_VALID_CLM_IND", length = 1)
	private String liDrugValidClmInd;
	
	@Column(name = "REC_MATCH_IND", length = 1)
	private String recMatchInd;
}
