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
 * Provides the base persistence definition
 * of the CaLuFullscaleStatusT entity. @author Anil Wagh
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CA_LU_FULLSCALE_STATUS_T")
public class CaLuFullscaleStatusT implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "STATUS_CD", unique = true, nullable = false, length = 3)
	private String statusCd;
	
	@Column(name = "STATUS_DESC", length = 50)
	private String statusDesc;
	
	@Column(name = "STATUS_VIEW", length = 1)
	private String statusView;
	
	@Column(name = "STATUS_SEQ", nullable = false, precision = 5, scale = 0)
	private Integer statusSeq;
	
	@Column(name = "STATUS_FILTER", length = 5)
	private String statusFilter;
	
	@Column(name = "STATUS_CLS_IND", length = 1)
	private String statusClsInd;
	
	@Column(name = "STATUS_TYPE", nullable = false, length = 15)
	private String statusType;
	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaAudFullscaleT> caAudFullscaleTs = new
	 * HashSet<CaAudFullscaleT>(0);
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaInvstFsStatusHistT>
	 * caInvstFsStatusHistTs = new HashSet<CaInvstFsStatusHistT>(0);
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaInvstFullscaleT> caInvstFullscaleTs =
	 * new HashSet<CaInvstFullscaleT>(0);
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaAudFsHistT> caAudFsHistTs = new
	 * HashSet<CaAudFsHistT>(0);
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaInvstFsHistT> caInvstFsHistTs = new
	 * HashSet<CaInvstFsHistT>(0);
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
	 * "caLuFullscaleStatusT") private Set<CaAudFsStatusHistT> caAudFsStatusHistTs =
	 * new HashSet<CaAudFsStatusHistT>(0);
	 */

}