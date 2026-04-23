package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
/*import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.SequenceGenerator;*/
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * FadsLuValuesTId entity. @author awagh
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
public class CaUniverseTPK  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CT_BATCH_ID", unique = true, nullable = false, precision = 14)
	private Integer ctBatchId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CT_BATCH_DATE")
	private Date ctBatchDate;
	
	@Column(name = "HDR_CLM_TCN", unique = true, nullable = false, length = 17)
	private String hdrClmTcn;
	
	@Column(name = "LI_NUM", unique = true, nullable = false, length = 4)
	private String liNum;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "HDR_CLM_PD_DT")
	private Date hdrClmPdDt;

}
