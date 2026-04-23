package com.optum.fads.caseentry.api.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CaseBatchDataDTO  implements Serializable {
	private static final long serialVersionUID = 1L;
    @NotBlank(message = "Invalid Case Type")
    @NotNull(message = "Invalid Case Type")
    @Size(min = 2, max = 2, message = "Invalid Case Type")
	private String caseType;
    @NotBlank(message = "Invalid Provider Role")
    @NotNull(message = "Invalid Provider Role")
    @Size(min = 2, max = 2, message = "Invalid Provider Role")
	private String providerRole;
	private String prmNodeCd;

	private String caseYear;
	private String protectStatus;
	private String invstTypeCd;
	private String caseSourceCd;
	private String  caseStatus;
	private String caseStatusDate;
	private String caseIssue;
	private String assignUser;
	private String assignSection;
	private String assignDate;
	private String uiSystemId;
    @Valid
	private List <BasicClaimData> basicClaimDataList;
	
}
