package com.optum.fads.caseentry.api.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The base persistence definition of
 * the CaInvstPrAssignT entity. 
 *  @author Anil Wagh
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_INVST_PR_ASSIGN_T")
public  class CaInvstPrAssignT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CaInvstPrAssignTPK id;
	
	@ManyToOne
	@JoinColumn(name = "ASSIGN_USR")
	private UiUserBase uiUserBase;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ASSIGN_SECTION_CD")
	private CaLuPrelimSectionT caLuPrelimSectionT;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "ASSIGN_DATE", length = 7)
	private Date assignDate;
	
	@Column(name = "EMAIL_SENT", length = 1)
	private String emailSent;

}