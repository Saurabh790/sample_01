package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the SE_SUR_MODULE database table.
 * 
 */
@Entity
@Data
@Table(name="SE_SUR_MODULE")
public class SeSurModule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUR_MODULE_ID")
	private String surModuleId;

	@Column(name="SUR_MODULE_NAME")
	private String surModuleName;

	//bi-directional many-to-one association to SeSurGrpModAccess
	@OneToMany(mappedBy="seSurModule",fetch = FetchType.EAGER)
	private List<SeSurGrpModAccess> seSurGrpModAccesses;

	

}