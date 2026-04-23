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
 * The persistent class for the CA_LU_CASE_TYPE_T database table.
 * 
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_LU_CASE_TYPE_T")
public class CaLuCaseTypeT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TYPE_CD", unique = true, nullable = false, length = 5)
	private String typeCd;
	
	@Column(name = "TYPE_DESC", length = 50)
	private String typeDesc;
	
	@Column(name = "TYPE_VIEW", length = 1)
	private String typeView;
	
	@Column(name = "TYPE_SEQ", precision = 5)
	private Integer typeSeq;
	
	@Column(name = "BATCH_VIEW", length = 1)
	private String batchView;

}
