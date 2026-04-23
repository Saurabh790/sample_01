package com.optum.fads.caseentry.api.repo;

import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.optum.fads.caseentry.api.domain.UiUserBase;
@Transactional
@Repository
public interface UserRepository extends JpaRepository<UiUserBase, String>, JpaSpecificationExecutor<UiUserBase> {
	Optional<UiUserBase> findByUiEMailAddressOrUiUserId(String userEmail, String userId);
	
}
