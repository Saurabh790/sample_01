package com.optum.fads.caseentry.api.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CaLuFullscaleSectionT entity provides the base persistence definition of
 * the CaLuFullscaleSectionT entity. @author Anil Wagh
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CA_LU_FULLSCALE_SECTION_T")
public class CaLuFullscaleSectionT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SECTION_CD", unique = true, nullable = false, length = 3)
	private String sectionCd;
	
	@Column(name = "SECTION_DESC", length = 50)
	private String sectionDesc;
	
	@Column(name = "SECTION_VIEW", length = 1)
	private String sectionView;
	
	@Column(name = "SECTION_SEQ", nullable = false, precision = 5, scale = 0)
	private Integer sectionSeq;
	
	@Column(name = "SECTION_FILTER", length = 5)
	private String sectionFilter;
	
	@Column(name = "SECTION_TYPE", nullable = false, length = 15)
	private String sectionType;
	
}