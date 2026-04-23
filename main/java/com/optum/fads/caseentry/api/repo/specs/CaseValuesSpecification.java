/**
 * 
 */
package com.optum.fads.caseentry.api.repo.specs;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.optum.fads.caseentry.api.common.constants.CaseEntryConstants;
import com.optum.fads.caseentry.api.domain.CaLuCaseSourceT;
import com.optum.fads.caseentry.api.domain.CaLuCaseTypeT;
import com.optum.fads.caseentry.api.domain.CaLuFullscaleIssueT;
import com.optum.fads.caseentry.api.domain.CaLuFullscaleSectionT;
import com.optum.fads.caseentry.api.domain.CaLuFullscaleStatusT;
import com.optum.fads.caseentry.api.domain.CaLuPrelimIssueT;
import com.optum.fads.caseentry.api.domain.CaLuPrelimSectionT;
import com.optum.fads.caseentry.api.domain.CaLuPrelimStatusT;
import com.optum.fads.caseentry.api.domain.CaLuValuesT;
import com.optum.fads.caseentry.api.domain.FadsLuValuesT;
import com.optum.fads.caseentry.api.domain.SeCaseGrpNodeAccess;
import com.optum.fads.caseentry.api.domain.UiUserBase;


/**
 * @author awagh
 *
 */
public class CaseValuesSpecification{

	public static Specification<CaLuCaseSourceT> findCaseSourcesSpecification(String sourceFilterIn) {

		return new Specification<CaLuCaseSourceT>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<CaLuCaseSourceT> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate sourceCodePredicate = criteriaBuilder.equal(root.get("sourceFilter"), sourceFilterIn);
				Predicate sourceViewPredicate = criteriaBuilder.equal(root.get("sourceView"), CaseEntryConstants.ONE);
				Predicate sourceValuesPredicate = criteriaBuilder.and(sourceCodePredicate, sourceViewPredicate); 
				return query.where(sourceValuesPredicate).getRestriction();
			}
		};
	}	
	
	public static Specification<CaLuCaseTypeT> findCaseTypeSpecification() {

		return new Specification<CaLuCaseTypeT>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<CaLuCaseTypeT> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate caseTypeViewPredicate = criteriaBuilder.equal(root.get("batchView"), CaseEntryConstants.ONE); 
				return query.where(caseTypeViewPredicate).getRestriction();
			}
		};
	}	
	
	public static Specification<CaLuPrelimStatusT> findPrelimStatusSpecification(String statusFilterIn) {

		return new Specification<CaLuPrelimStatusT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuPrelimStatusT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate prelimStatusPredicate = cb.equal(root.get("statusFilter"),statusFilterIn);
				Predicate prelimStatusViewPredicate = cb.equal(root.get("statusView"),CaseEntryConstants.ONE);
				Predicate prelimStatusValuesPredicate = cb.and(prelimStatusPredicate, prelimStatusViewPredicate);
				return query.where(prelimStatusValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuPrelimSectionT> findPrelimSectionSpecification(String sectionFilterIn) {

		return new Specification<CaLuPrelimSectionT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuPrelimSectionT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate prelimSectionPredicate = cb.equal(root.get("sectionFilter"),sectionFilterIn);		
				Predicate prelimSectionViewPredicate = cb.equal(root.get("sectionView"), CaseEntryConstants.ONE);
				Predicate prelimSectionValuesPredicate = cb.and(prelimSectionPredicate, prelimSectionViewPredicate);
				return query.where(prelimSectionValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuFullscaleStatusT> findFullscaleStatusSpecification(String statusFilterIn) {

		return new Specification<CaLuFullscaleStatusT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuFullscaleStatusT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate fullscaleStatusPredicate = cb.equal(root.get("statusFilter"),statusFilterIn);
				Predicate fullscaleStatusViewPredicate = cb.equal(root.get("statusView"),CaseEntryConstants.ONE);
				Predicate fullscaleStatusValuesPredicate = cb.and(fullscaleStatusPredicate, fullscaleStatusViewPredicate);
				return query.where(fullscaleStatusValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuFullscaleSectionT> findFullscaleSectionSpecification(String sectionFilterIn) {

		return new Specification<CaLuFullscaleSectionT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuFullscaleSectionT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate fullscaleSectionPredicate = cb.equal(root.get("sectionFilter"),sectionFilterIn);		
				Predicate fullscaleSectionViewPredicate = cb.equal(root.get("sectionView"), CaseEntryConstants.ONE);
				Predicate fullscaleSectionValuesPredicate = cb.and(fullscaleSectionPredicate, fullscaleSectionViewPredicate);
				return query.where(fullscaleSectionValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuPrelimIssueT> findPrelimIssueSpecification(String issueFilterIn) {

		return new Specification<CaLuPrelimIssueT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuPrelimIssueT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get("issueSeq")));
				Predicate prelimIssuePredicate = cb.equal(root.get("issueFilter"), issueFilterIn);		
				Predicate prelimIssueViewPredicate = cb.equal(root.get("issueView"), CaseEntryConstants.ONE);
				Predicate prelimIssueValuesPredicate = cb.and(prelimIssuePredicate, prelimIssueViewPredicate);
				return query.where(prelimIssueValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuFullscaleIssueT> findFullscaleIssueSpecification(String issueFilterIn) {

		return new Specification<CaLuFullscaleIssueT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuFullscaleIssueT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get("issueSeq")));
				Predicate fullscaleIssuePredicate = cb.equal(root.get("issueFilter"), issueFilterIn);	
				Predicate fullscaleIssueViewPredicate = cb.equal(root.get("issueView"), CaseEntryConstants.ONE);
				Predicate fullscaleIssueValuesPredicate = cb.and(fullscaleIssuePredicate, fullscaleIssueViewPredicate);
				return query.where(fullscaleIssueValuesPredicate).getRestriction();
			}
		};
	}
	
	public static Specification<CaLuValuesT> findCaLuValuesSpecification(String fieldIdIn) {

		return new Specification<CaLuValuesT>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CaLuValuesT> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.orderBy(cb.asc(root.get("id").get("luSeq")));
				Predicate caLuValuePredicate = cb.equal(root.get("id").get("fieldId"), fieldIdIn);	
				Predicate caLuViewPredicate = cb.equal(root.get("luView"), CaseEntryConstants.ONE);
				Predicate caLuValuesPredicate = cb.and(caLuValuePredicate, caLuViewPredicate);
				return query.where(caLuValuesPredicate).getRestriction();
			}
		};
	}
	
	// Get Users with case group ID <> -99
	public static Specification<UiUserBase>  findAnalystsSpecification() {

		return new Specification<UiUserBase>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<UiUserBase> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Predicate caseGrpPredicate = cb.notEqual(root.get("seUsrGrp").get("seCaseGrp").get("caseGrpId"), -99);		
				return query.where(caseGrpPredicate).getRestriction();
			}
		};
	}
	
	// Get Case Group ID for case Node ID = input and caseNodeAccessType = "C" - create/write/read
		public static Specification<SeCaseGrpNodeAccess> findCaseGroupsSpecification(String caseNodeId) {

			return new Specification<SeCaseGrpNodeAccess>() {
				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<SeCaseGrpNodeAccess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
					
					Predicate caseGrpPredicate = cb.equal(root.get("id").get("caseNodeId"), caseNodeId);
					Predicate caseNodeAccessPredicate = cb.equal(root.get("caseNodeAccessType"), "C");
					Predicate caseGrpNodeAccessPredicate = cb.and(caseGrpPredicate, caseNodeAccessPredicate);
					return query.where(caseGrpNodeAccessPredicate).getRestriction();
				}
			};
		}
		
		// Get Case Node IDs for input case Group ID  and caseNodeAccessType = "C" - create/write/read
				public static Specification<SeCaseGrpNodeAccess> findCaseNodesSpecification(Integer caseGroupId) {

					return new Specification<SeCaseGrpNodeAccess>() {
						private static final long serialVersionUID = 1L;

						@Override
						public Predicate toPredicate(Root<SeCaseGrpNodeAccess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
							
							Predicate caseGrpPredicate = cb.equal(root.get("id").get("caseGrpId"), caseGroupId);
							Predicate caseNodeAccessPredicate = cb.equal(root.get("caseNodeAccessType"), "C");
							Predicate caseGrpNodeAccessPredicate = cb.and(caseGrpPredicate, caseNodeAccessPredicate);
							return query.where(caseGrpNodeAccessPredicate).getRestriction();
						}
					};
				}
	
}
