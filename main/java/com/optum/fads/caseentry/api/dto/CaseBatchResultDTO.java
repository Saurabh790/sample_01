package com.optum.fads.caseentry.api.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@ToString
@NoArgsConstructor
public class CaseBatchResultDTO  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String batchId;
	private String createDate;
	private String batchCreator;
	private String numberOfCases;

}
