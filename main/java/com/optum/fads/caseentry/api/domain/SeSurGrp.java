package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the SE_SUR_GRP database table.
 * 
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name="SE_SUR_GRP")
public class SeSurGrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUR_GRP_ID")
	private long surGrpId;

	@Column(name="SUR_GRP_NAME")
	private String surGrpName;

	//bi-directional many-to-one association to SeSurGrpModAccess
	@OneToMany(mappedBy="seSurGrp",fetch = FetchType.EAGER)
	private List<SeSurGrpModAccess> seSurGrpModAccesses;

	//bi-directional many-to-one association to SeUsrGrp
	@OneToMany(mappedBy="seSurGrp")
	private List<SeUsrGrp> seUsrGrps;

}