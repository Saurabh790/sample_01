/**
 * 
 */
package com.optum.fads.caseentry.api.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author sbiry
 *
 */
public interface IUserDetailsService extends UserDetailsService
{

	public UserDetails loadUserByUsername(String username);
}
