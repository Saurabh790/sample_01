/**
 * 
 */
package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author awagh
 *
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FADS_CONFIG_T")
public class FadsConfigT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "OPTION_CODE", unique = true, nullable = false, precision = 7)
	private Long optionCode;
	@Column(name = "OPTION_VALUE", length = 50)
	private String optionValue;
	@Column(name = "DESCRIPTION", length = 4000)
	private String description;
}
