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
 * The persistent class for the SE_SUR_ACCESS database table.
 * 
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name="SE_SUR_ACCESS")
public class SeSurAccess implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="SUR_ACCESS_ID")
	private String surAccessId;

	@Column(name="SUR_ACCESS_NAME")
	private String surAccessName;

	//bi-directional many-to-one association to SeSurGrpModAccess
	@OneToMany(mappedBy="seSurAccess",fetch = FetchType.EAGER)
	private List<SeSurGrpModAccess> seSurGrpModAccesses;


}