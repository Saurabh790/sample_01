package com.optum.fads.caseentry.api.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CaLuPrelimSectionT entity provides the base persistence definition of
 * the CaLuPrelimSectionT entity. @author Anil Wagh
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CA_LU_PRELIM_SECTION_T")
public class CaLuPrelimSectionT implements java.io.Serializable {
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "caLuPrelimSectionT")
	private List<CaInvstPrAssignT> caInvstPrAssignTs;

}