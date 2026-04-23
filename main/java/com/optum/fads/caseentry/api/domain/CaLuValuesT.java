package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The base persistence definition of the
 * CaLuValuesT entity. @author Anil Wagh
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CA_LU_VALUES_T")
public class CaLuValuesT implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private CaLuValuesTPK id;
	
	@Column(name = "LU_CODE", length = 10)
	private String luCode;
	
	@Column(name = "LU_LABEL", length = 80)
	private String luLabel;
	
	@Column(name = "LU_VIEW", length = 2)
	private String luView;
	
	@Column(name = "LU_FILTER", length = 10)
	private String luFilter;
	
	@Column(name = "LU_SCORE", length = 5)
	private String luScore;

}