package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaLuPrelimStatusT entity. @author Shyam Biry
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_LU_PRELIM_STATUS_T")
public class CaLuPrelimStatusT implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "STATUS_CD", unique = true, nullable = false, length = 3)
	private String statusCd;
	
	@Column(name = "STATUS_DESC", length = 50)
	private String statusDesc;
	
	@Column(name = "STATUS_VIEW", length = 1)
	private String statusView;
	
	@Column(name = "STATUS_SEQ", precision = 5)
	private Integer statusSeq;
	
	@Column(name = "STATUS_FILTER", length = 5)
	private String statusFilter;
	
	@Column(name = "STATUS_CLS_IND", length = 1)
	private String statusClsInd;
	
	@Column(name = "STATUS_TYPE", length = 15)
	private String statusType;
}
