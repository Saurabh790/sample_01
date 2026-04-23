package com.optum.fads.caseentry.api.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import org.hibernate.annotations.Formula;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the UI_USER_BASE database table.
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Builder
@Entity
@Table(name = "UI_USER_BASE")
public class UiUserBase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UI_SYSTEM_ID", unique = true, nullable = false, length = 25)
	private String uiSystemId;

	@Column(name = "UI_CREATED_BY", length = 25)
	private String uiCreatedBy;

	// @Temporal(TemporalType.DATE)
	@Column(name = "UI_CREATED_DT")
	private LocalDateTime uiCreatedDt;

	@Column(name = "UI_E_MAIL_ADDRESS", nullable = false, length = 50)
	private String uiEMailAddress;

	@Column(name = "UI_FIRST_NAME", length = 20)
	private String uiFirstName;

	@Column(name = "UI_LAST_NAME", length = 40)
	private String uiLastName;

	@Column(name = "UI_TITLE", length = 15)
	private String uiTitle;

	@Column(name = "UI_USER_ID", nullable = false, length = 25)
	private String uiUserId;

	@Column(name = "UI_USER_INACTIVE", length = 1)
	private String uiUserInactive;

	@Formula(value = "CONCAT(UI_FIRST_NAME, ' ', UI_LAST_NAME)")
	private String userFullName;
	
	// bi-directional one-to-one association to SeUsrGrp
	@OneToOne(mappedBy = "uiUserBase")
	private SeUsrGrp seUsrGrp;
	
}
