package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/***
 * @author awagh2
 * The persistent class for the CASE_CONFIG_T database table. 
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CASE_CONFIG")
public class CaseConfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OPTION_CODE")
	private String optionCode;

	@Column(name = "OPTION_VALUE")
	private String optionValue;

}
