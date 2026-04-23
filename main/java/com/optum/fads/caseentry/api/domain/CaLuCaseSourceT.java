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
 * The persistent class for the CA_LU_CASE_SOURCE_T database table. 
 * 
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_LU_CASE_SOURCE_T")
public class CaLuCaseSourceT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SOURCE_CD", unique = true, nullable = false, length = 3)
	private String sourceCd;
	
	@Column(name = "SOURCE_DESC", length = 50)
	private String sourceDesc;
	
	@Column(name = "SOURCE_VIEW", length = 1)
	private String sourceView;
	
	@Column(name = "SOURCE_SEQ", precision = 5)
	private Integer sourceSeq;
	
	@Column(name = "SOURCE_FILTER", length = 5)
	private String sourceFilter;
	
	@Column(name = "SOURCE_SCORE", length = 5)
	private String sourceScore;
	

}
