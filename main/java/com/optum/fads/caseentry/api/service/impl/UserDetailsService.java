package com.optum.fads.caseentry.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.optum.fads.caseentry.api.domain.SeSurGrpModAccess;
import com.optum.fads.caseentry.api.domain.UiUserBase;
import com.optum.fads.caseentry.api.dto.AppUser;
import com.optum.fads.caseentry.api.dto.ModuleAccess;
import com.optum.fads.caseentry.api.dto.AccessLevel;
import com.optum.fads.caseentry.api.dto.Role;
import com.optum.fads.caseentry.api.exception.CaseEntryApiException;
import com.optum.fads.caseentry.api.repo.UserRepository;
import com.optum.fads.caseentry.api.service.IUserDetailsService;

@Service
public class UserDetailsService implements IUserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UiUserBase> optional = userRepository.findByUiEMailAddressOrUiUserId(username, username);
//		optional.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));
		AppUser user = new AppUser();
		if (optional.isPresent()) {
			UiUserBase uiUserBase = optional.get();
			user.setUserEmail(uiUserBase.getUiEMailAddress());
			user.setUserId(uiUserBase.getUiUserId());
			user.setUserSystemId(uiUserBase.getUiSystemId());
			Role role = new Role();
			role.setId(Long.valueOf(uiUserBase.getSeUsrGrp().getSeSurGrp().getSurGrpId()).toString());
			role.setRoleName(uiUserBase.getSeUsrGrp().getSeSurGrp().getSurGrpName());
			List<SeSurGrpModAccess> surAccesses = uiUserBase.getSeUsrGrp().getSeSurGrp().getSeSurGrpModAccesses();
	
			ArrayList<AccessLevel> accesses = new ArrayList<AccessLevel>();
			surAccesses.forEach(surModuleAccess -> {
				accesses.add(new AccessLevel(surModuleAccess.getId().getSurModuleId(),
						ModuleAccess.getByName(surModuleAccess.getId().getSurModuleId()).toString(),
						surModuleAccess.getSeSurModule().getSurModuleName(),
						surModuleAccess.getSeSurAccess().getSurAccessId()));
	
			});
	
			role.setAllowedAccesses(accesses);
			user.setRole(role);
		} else {
			throw new CaseEntryApiException("Not found: " + username);
		}
		return user;

	}

	

}
