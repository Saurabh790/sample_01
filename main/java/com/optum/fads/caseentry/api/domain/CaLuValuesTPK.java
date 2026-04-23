package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * CaLuValuesTPK entity. @author Anil Wagh
 */
@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Embeddable
public class CaLuValuesTPK implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "FIELD_ID", nullable = false, length = 30)
	private String fieldId;
	
	@Column(name = "LU_VALUE", nullable = false, length = 3)
	private String luValue;
	
	@Column(name = "LU_SEQ", nullable = false, precision = 5, scale = 0)
	private Integer luSeq;

}