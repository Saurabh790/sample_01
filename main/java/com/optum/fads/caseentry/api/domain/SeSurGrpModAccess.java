package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;


/**
 * The persistent class for the SE_SUR_GRP_MOD_ACCESS database table.
 * 
 */
@Entity
@Data
@Table(name="SE_SUR_GRP_MOD_ACCESS")
public class SeSurGrpModAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SeSurGrpModAccessPK id;

	//bi-directional many-to-one association to SeSurAccess
	@ManyToOne
	@JoinColumn(name="SUR_GRP_MOD_ACCESS")
	private SeSurAccess seSurAccess;

	//bi-directional many-to-one association to SeSurGrp
	@ManyToOne()
	@JoinColumn(name="SUR_GRP_ID", insertable = false , updatable = false)
	private SeSurGrp seSurGrp;

	//bi-directional many-to-one association to SeSurModule
	@ManyToOne
	@JoinColumn(name="SUR_MODULE_ID", insertable = false , updatable = false)
	private SeSurModule seSurModule;


}