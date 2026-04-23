package com.optum.fads.caseentry.api.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author awagh
 *
 */
@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class CaseIndValues implements Serializable{

	private static final long serialVersionUID = 1L;

	private String caseValidValue;
	private List<CaseValuesDTO> caseValues;
}
