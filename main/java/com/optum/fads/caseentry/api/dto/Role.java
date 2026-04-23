/**
 * 
 */
package com.optum.fads.caseentry.api.dto;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

/**
 * @author sbiry
 *
 */
@Data
public class Role implements GrantedAuthority {
	private static final long serialVersionUID = 1L;

	 private String id;
	 private String roleName;
	 
	 private ArrayList<AccessLevel> allowedAccesses = new ArrayList<>();

	 
	@Override
	public String getAuthority() {
		return id;
	}
	
	
}
