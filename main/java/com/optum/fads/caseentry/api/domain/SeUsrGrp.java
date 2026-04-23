package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the SE_USR_GRP database table.
 * 
 */
@Getter
@Setter
@Entity
@Table(name="SE_USR_GRP")
public class SeUsrGrp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="UI_SYSTEM_ID")
	private String uiSystemId;


	@ManyToOne
	@JoinColumn(name="FADS_SUR_GRP_ID")
	private SeSurGrp seSurGrp;

	@ManyToOne
	@JoinColumn(name="FADS_CASE_GRP_ID")
	private SeCaseGrp seCaseGrp;

	//bi-directional one-to-one association to UiUserBase
	@OneToOne
	@JoinColumn(name="UI_SYSTEM_ID")
	private UiUserBase uiUserBase;


}
