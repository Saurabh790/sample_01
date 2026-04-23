package com.optum.fads.caseentry.api.dto;

import java.util.List;

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
public class ClaimDataParams {
	private List<String> basicClaimData;
}
