package com.optum.fads.caseentry.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * @author awagh
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasicClaimData implements Serializable {
	private static final long serialVersionUID = 1L;

    @NotBlank(message = "Invalid Provider ID")
    @NotNull(message = "Provider ID is NULL")
    @Size(min = 5, max = 25, message = " Provider ID must be of 5 -25 characters")
	private String providerId;

    @NotBlank(message = "Invalid Hdr Claim TCN ")
    @NotNull(message = "Hdr Claim TCN is NULL")
    @Size(min = 0, max = 17, message = " Hdr Claim TCN must be of 5 -17 characters")
	private String hdrClaimTCN;

    @NotBlank(message = "Invalid liNum")
    @NotNull(message = "liNum is NULL")
    @Size(min = 0, max = 4, message = " liNum must be of 0 -4 characters")
	private String liNum;

    @NotBlank(message = "Invalid hdrClaimPdDt")
    @NotNull(message = "hdrClaimPdDt is NULL")
	private String hdrClaimPdDt;

    @NotBlank(message = "Invalid liDrugValidClmInd")
    @NotNull(message = "liDrugValidClmInd is NULL")
	private String liDrugValidClmInd;
}
