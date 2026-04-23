package com.optum.fads.caseentry.api.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * provides the base persistence definition of
 * the CaIntelligentKeyT entity. 
 * @author Anil Wagh
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name = "CA_INTELLIGENT_KEY_T")
public class CaIntelligentKeyT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CASE_ID", unique = true, nullable = false, precision = 7, scale = 0)
	private Integer caseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVE_USER")
	private UiUserBase uiUserBaseByActiveUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATED_BY", nullable = false)
	private UiUserBase uiUserBaseByCreatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UPDATED_BY", nullable = false)
	private UiUserBase uiUserBaseByUpdatedBy;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CA_SCND_NODE_CD")
	private CaLuScndNodeT caLuScndNodeT;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CA_PRM_NODE_CD")
	private CaLuPrmNodeT caLuPrmNodeT;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CASE_TYPE_CD", nullable = false)
	private CaLuCaseTypeT caLuCaseTypeT;
	
	@Column(name = "CA_YEAR_ID", precision = 4, scale = 0)
	private Short caYearId;
	
	@Column(name = "CA_SEQUENCE_ID", precision = 7, scale = 0)
	private Integer caSequenceId;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DT", nullable = false, length = 7)
	private Date createdDt;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DT", nullable = false, length = 7)
	private Date updatedDt;
	
	@Column(name = "DELETE_FLAG", length = 1)
	private String deleteFlag;
	
	@Column(name = "LOCK_FLAG", length = 1)
	private String lockFlag;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "USER_LOGIN_DT", length = 7)
	private Date userLoginDt;	

	}