package com.optum.fads.caseentry.api.service.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.optum.fads.caseentry.api.domain.CaUniverseBatchT;
import com.optum.fads.caseentry.api.domain.FadsConfigT;
import com.optum.fads.caseentry.api.domain.SeCaseGrpNodeAccess;
import com.optum.fads.caseentry.api.domain.SeUsrGrp;
import com.optum.fads.caseentry.api.domain.UiUserBase;
import com.optum.fads.caseentry.api.dto.BasicClaimData;
import com.optum.fads.caseentry.api.dto.CaseBatchDataDTO;
import com.optum.fads.caseentry.api.dto.CaseBatchResultDTO;
import com.optum.fads.caseentry.api.dto.CaseIndValues;
import com.optum.fads.caseentry.api.dto.CaseInfo;
import com.optum.fads.caseentry.api.dto.CaseValuesDTO;
import com.optum.fads.caseentry.api.exception.CaseEntryApiException;
import com.optum.fads.caseentry.api.repo.CaLuCaseSourceRepository;
import com.optum.fads.caseentry.api.repo.CaLuCaseTypesRepository;
import com.optum.fads.caseentry.api.repo.CaLuFullscaleIssueRepository;
import com.optum.fads.caseentry.api.repo.CaLuFullscaleSectionRepository;
import com.optum.fads.caseentry.api.repo.CaLuFullscaleStatusRepository;
import com.optum.fads.caseentry.api.repo.CaLuPrelimIssueRepository;
import com.optum.fads.caseentry.api.repo.CaLuPrelimSectionRepository;
import com.optum.fads.caseentry.api.repo.CaLuPrelimStatusRepository;
import com.optum.fads.caseentry.api.repo.CaLuValuesRepository;
import com.optum.fads.caseentry.api.repo.CaUniverseBatchRepository;
import com.optum.fads.caseentry.api.repo.CaseConfigRepository;
import com.optum.fads.caseentry.api.repo.FadsConfigRepository;
import com.optum.fads.caseentry.api.repo.SeCaseGrpNodeAccessRepository;
import com.optum.fads.caseentry.api.repo.SeUsrGrpRepository;
import com.optum.fads.caseentry.api.repo.UserRepository;
import com.optum.fads.caseentry.api.repo.specs.CaseValuesSpecification;
import com.optum.fads.caseentry.api.service.ICaseValuesService;
//import com.optum.fads.caseentry.api.snowflakeRepo.DmClaimDrugRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CaseValuesService  implements ICaseValuesService {

	@Autowired
	private CaLuCaseSourceRepository caLuCaseSourceRepository;
	@Autowired
	private CaLuCaseTypesRepository caLuCaseTypesRepository;
	@Autowired
	private CaLuPrelimStatusRepository caLuPrelimStatusRepository;
	@Autowired
	private CaLuFullscaleStatusRepository caLuFullscaleStatusRepository;
	@Autowired
	private CaLuPrelimIssueRepository caLuPrelimIssueRepository;
	@Autowired
	private CaLuFullscaleIssueRepository caLuFullscaleIssueRepository;
	@Autowired
	private CaLuPrelimSectionRepository caLuPrelimSectionRepository;
	@Autowired
	private CaLuFullscaleSectionRepository caLuFullscaleSectionRepository;
	@Autowired
	private FadsConfigRepository fadsConfigRepository;
	@Autowired
	private CaLuValuesRepository caLuValuesRepository;
	@Autowired
	private CaseConfigRepository caseConfigRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CaUniverseBatchRepository caUniverseBatchRepository;
	@Autowired
	private SeCaseGrpNodeAccessRepository seCaseGrpNodeAccessRepository;
	@Autowired
	private SeUsrGrpRepository seUsrGrpRepository;

	
	@Transactional(readOnly = true)
	@Override
	public CaseInfo getCaseInfo(String userId) { 
		CaseInfo caseInfo = new CaseInfo();
		List<CaseIndValues> caseIndValuesList= new ArrayList<>();
		
		/* Get Case Year.
		 * What would be the case year in February 2022 - calendar year 2022. 
		 * But for a Fiscal year, if for example a customer is using FY (then come this July 1, 
		 *the year in the system would automatically display 2023 (for cases being created in FY start July 1, 2022- end June 30, 2023)
		 */
		 int yearId = Calendar.getInstance().get(Calendar.YEAR);
		 int monthId = Calendar.getInstance().get(Calendar.MONTH);
		 int dayId = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		 int ikeyFisYrMonth=Integer.parseInt(caseConfigRepository.getOptionValue(CaseEntryConstants.FISCAL_YEAR_MONTH));
		 int iKeyFisyrDay=Integer.parseInt(caseConfigRepository.getOptionValue(CaseEntryConstants.FISCAL_YEAR_DAY));
		 if (monthId > ikeyFisYrMonth) {
			 yearId++;
		 } else if (monthId == ikeyFisYrMonth) {
			if (dayId > iKeyFisyrDay) {
				yearId++;
			}
		}
		 caseInfo.setCaseYear(String.valueOf(yearId));
			
		// Get Case ID - Prm Nodes - PAU,MAU,MIU,PIU,MCO
		 CaseIndValues caseIndPrmNodeValues = getCaseIdValues(userId);
		 caseIndValuesList.add(caseIndPrmNodeValues);
		 CaseIndValues caseIndTypeValues = getCaseTypeValues();
		 caseIndValuesList.add(caseIndTypeValues);
		 CaseIndValues caseInvstTypeValues = getCaseInvstTypeValues();
		 caseIndValuesList.add(caseInvstTypeValues);
		 CaseIndValues caseIndFadsLuValues = getCaseProvRoleValues();
		 caseIndValuesList.add(caseIndFadsLuValues);
		caseInfo.setCaseInfoValues(caseIndValuesList);
		
		return caseInfo;
	}

	public CaseIndValues getCaseIdValues(String userId){

		CaseIndValues caseIndPrmNodeValues = new CaseIndValues();

		Optional<SeUsrGrp> optional = seUsrGrpRepository.findById(userId);
		SeUsrGrp seUsrGrp = optional.get();
		// Get Case ID - Prm Nodes - PAU,MAU,MIU,PIU,MCO as per Use access	
		Specification<SeCaseGrpNodeAccess> finalCaseGroupIdsSpecs
			= CaseValuesSpecification.findCaseNodesSpecification(seUsrGrp.getSeCaseGrp().getCaseGrpId());
		List<SeCaseGrpNodeAccess> seCaseGrpNodeAccessList = seCaseGrpNodeAccessRepository.findAll(finalCaseGroupIdsSpecs);

		 List<CaseValuesDTO> caLuPrmNodeDtoList = new ArrayList<>();
		 for (SeCaseGrpNodeAccess seCaseGrpNodeAccess : seCaseGrpNodeAccessList) {
				 CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
				 caseValuesDTO.setCode(seCaseGrpNodeAccess.getId().getCaseNodeId());
				 caseValuesDTO.setDescription(seCaseGrpNodeAccess.getId().getCaseNodeId());
				 caseValuesDTO.setFilter(CaseEntryConstants.EMPTY_STR);
				 caLuPrmNodeDtoList.add(caseValuesDTO);
		 }
		caseIndPrmNodeValues.setCaseValidValue(CaseEntryConstants.CASE_NODES);
		caseIndPrmNodeValues.setCaseValues(caLuPrmNodeDtoList);
		
		return caseIndPrmNodeValues;
	}
	
	public CaseIndValues getCaseTypeValues(){

		// Get Case Types 
		CaseIndValues caseIndTypeValues = new CaseIndValues();
		Specification<CaLuCaseTypeT> caLuCaseTypesSpecs = CaseValuesSpecification.findCaseTypeSpecification();
		List<CaLuCaseTypeT> caLuCaseTypeTList = caLuCaseTypesRepository.findAll(caLuCaseTypesSpecs);
//		List<CaseValuesDTO> caLuTypeDtoList = caseTypesMapper.converToCaseValuesDTOs(caLuCaseTypeTList);
		List<CaseValuesDTO> caLuTypeDtoList = new ArrayList<>();
		for (CaLuCaseTypeT caLuCaseTypeT : caLuCaseTypeTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuCaseTypeT.getTypeCd());
			caseValuesDTO.setDescription(caLuCaseTypeT.getTypeDesc());
			caLuTypeDtoList.add(caseValuesDTO);
		}
		caseIndTypeValues.setCaseValidValue(CaseEntryConstants.CASE_TYPES);
		caseIndTypeValues.setCaseValues(caLuTypeDtoList);
				
		return caseIndTypeValues;
	}
	
	public CaseIndValues getCaseInvstTypeValues(){
		
		// Get Case Investigation Types 
		CaseIndValues caseInvstTypeValues = new CaseIndValues();
		Specification<CaLuValuesT> caLuValuesSpecs = CaseValuesSpecification.findCaLuValuesSpecification(CaseEntryConstants.INVESTIGATION_TYPE_CODE);
		List<CaLuValuesT> caLuValuesTList = caLuValuesRepository.findAll(caLuValuesSpecs);
		List<CaseValuesDTO> caLuValuesDtoList = new ArrayList<>();
		for (CaLuValuesT caLuValuesT : caLuValuesTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuValuesT.getId().getLuValue());
			caseValuesDTO.setDescription(caLuValuesT.getLuLabel());
			caLuValuesDtoList.add(caseValuesDTO);
		}
		caseInvstTypeValues.setCaseValidValue(CaseEntryConstants.CASE_INVESTIGATION_TYPE);
		caseInvstTypeValues.setCaseValues(caLuValuesDtoList);
						
		return caseInvstTypeValues;
	}
	
	public CaseIndValues getCaseProvRoleValues(){
		
		// Get CA LU Values  - for provider roles
				CaseIndValues caseIndFadsLuValues = new CaseIndValues(); 
				Specification<CaLuValuesT> finalCaLuValuesSpecs = CaseValuesSpecification.findCaLuValuesSpecification(CaseEntryConstants.PROVIDER_ROLE);
				List<CaLuValuesT> caLuValuesTList = caLuValuesRepository.findAll(finalCaLuValuesSpecs);
							
				List<CaseValuesDTO> caLuValuesDtoList = new ArrayList<>();
				for (CaLuValuesT caLuValuesT : caLuValuesTList) {
					CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
					caseValuesDTO.setCode(caLuValuesT.getLuCode());
					caseValuesDTO.setDescription(caLuValuesT.getLuLabel());
					caseValuesDTO.setFilter(caLuValuesT.getLuCode());
					caLuValuesDtoList.add(caseValuesDTO);
				}
							 
				caseIndFadsLuValues.setCaseValidValue(CaseEntryConstants.FADS_LU_VALUE);
				caseIndFadsLuValues.setCaseValues(caLuValuesDtoList);	
						
				return caseIndFadsLuValues;	
	}
	
	@Override
	public CaseIndValues getCaseSourceValues(String prmNode) { 

		// Get Case Sources - Attorney General, Audit Review, CMS Contractor,FADS Peer Grouping Report,Fraud Algorithm 
		//					Fraud Hotline, Fraud Website Internal 
		CaseIndValues caseIndSourceValues = new CaseIndValues(); 
		Specification<CaLuCaseSourceT> caseSourceSpecs = CaseValuesSpecification.findCaseSourcesSpecification(prmNode);
		List<CaLuCaseSourceT> caLuCaseSourceTList = caLuCaseSourceRepository.findAll(caseSourceSpecs);
//		List<CaseValuesDTO> caLuCaseSourceDtoList = caseSourcesMapper.converToCaseValuesDTOs(caLuCaseSourceTList);
		 List<CaseValuesDTO> caLuCaseSourceDtoList = new ArrayList<>();
		 for (CaLuCaseSourceT caLuCaseSourceT : caLuCaseSourceTList) {
			 CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			 caseValuesDTO.setCode(caLuCaseSourceT.getSourceCd());
			 caseValuesDTO.setDescription(caLuCaseSourceT.getSourceDesc());
			 caseValuesDTO.setFilter(caLuCaseSourceT.getSourceFilter());
			 caLuCaseSourceDtoList.add(caseValuesDTO);
		 }
		caseIndSourceValues.setCaseValidValue(CaseEntryConstants.CASE_SOURCES);
		caseIndSourceValues.setCaseValues(caLuCaseSourceDtoList);
		
		
		return caseIndSourceValues;
	}
	/**
	 * Find case-related-values based on Case ID (prmNode) and investigation Type
	 */
	@Override
	public List<CaseIndValues> findCaseValues(String prmNode, String invstType) { 
		List<CaseIndValues> caseIndValuesList= new ArrayList<>();		 
		
		if (invstType.equals(CaseEntryConstants.INVESTIGATION_TYPE_PR)) {
			// Get Case Preliminary Statuses - Open, Initial Review, Closed, Pending
			 
			CaseIndValues caseIndPrelimStatusValues = getPreliminaryStatusValues(prmNode);
			caseIndValuesList.add(caseIndPrelimStatusValues);
			CaseIndValues caseIndPrelimSectionValues = getPreliminaryAssignmentSectionCodes(prmNode);
			caseIndValuesList.add(caseIndPrelimSectionValues);
			CaseIndValues caseIndPrelimIssueValues = getPreliminaryIssues(prmNode);
			caseIndValuesList.add(caseIndPrelimIssueValues);
			
		} else {
			
			CaseIndValues caseIndFullscaleStatusValues = getFullscaleStatusValues(prmNode);
			caseIndValuesList.add(caseIndFullscaleStatusValues);
			CaseIndValues caseIndFullscaleAssignmentSectionValues = getFullscaleAssignmentSectionCodes(prmNode);
			caseIndValuesList.add(caseIndFullscaleAssignmentSectionValues);
			CaseIndValues caseIndFullscaleIssueValues = getFullscaleIssues(prmNode);
			caseIndValuesList.add(caseIndFullscaleIssueValues);
		}
		CaseIndValues caseIndAnalysts = getCaseAnalysts(prmNode);
		caseIndValuesList.add(caseIndAnalysts);
		
		return caseIndValuesList;
	}
	
	public CaseIndValues getPreliminaryStatusValues(String prmNode){
		
		CaseIndValues caseIndPrelimStatusValues = new CaseIndValues();
		// prmNode input parm to spec - part of Case ID on the Batch Case Entry screen (CA_LU_PRM_NODE_T) 
		Specification<CaLuPrelimStatusT> finalPrelimStatusSpecs = CaseValuesSpecification.findPrelimStatusSpecification(prmNode);
		List<CaLuPrelimStatusT> caLuPrelimStatusTList = caLuPrelimStatusRepository.findAll(finalPrelimStatusSpecs);
//		List<CaseValuesDTO> caLuPrelimStatusDtoList = casePrelimStatusMapper.converToCaseValuesDTOs(caLuPrelimStatusTList);
		
		 List<CaseValuesDTO> caLuPrelimStatusDtoList = new ArrayList<>();
		 for (CaLuPrelimStatusT caLuPrelimStatusT : caLuPrelimStatusTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			 caseValuesDTO.setCode(caLuPrelimStatusT.getStatusCd());
			 caseValuesDTO.setDescription(caLuPrelimStatusT.getStatusDesc());
			 caseValuesDTO.setFilter(caLuPrelimStatusT.getStatusFilter());
			 caLuPrelimStatusDtoList.add(caseValuesDTO);
		 }
		 caseIndPrelimStatusValues.setCaseValidValue(CaseEntryConstants.CASE_STATUS);
		 caseIndPrelimStatusValues.setCaseValues(caLuPrelimStatusDtoList);
		 
		 return caseIndPrelimStatusValues;
		
	}
	
	// Get Preliminary Investigation Section codes 
	public CaseIndValues getPreliminaryAssignmentSectionCodes(String prmNode){
		
		CaseIndValues caseIndPrelimSectionValues = new CaseIndValues();
		Specification<CaLuPrelimSectionT> finalPrelimSectionSpecs = CaseValuesSpecification.findPrelimSectionSpecification(prmNode);
		List<CaLuPrelimSectionT> caLuPrelimSectionTList = caLuPrelimSectionRepository.findAll(finalPrelimSectionSpecs);
		 //	 List<CaseValuesDTO> caLuTypeDtoList = caseTypesMapper.converToCaseValuesDTOs(caLuCaseTypeTList);
		List<CaseValuesDTO> caLuPrelimSectionDtoList = new ArrayList<>();
		for (CaLuPrelimSectionT caLuPrelimSectionT : caLuPrelimSectionTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuPrelimSectionT.getSectionCd());
			caseValuesDTO.setDescription(caLuPrelimSectionT.getSectionDesc());
			caseValuesDTO.setFilter(caLuPrelimSectionT.getSectionFilter());
			caLuPrelimSectionDtoList.add(caseValuesDTO);
		}
		caseIndPrelimSectionValues.setCaseValidValue(CaseEntryConstants.CASE_SECTION);
		caseIndPrelimSectionValues.setCaseValues(caLuPrelimSectionDtoList);
		
		return caseIndPrelimSectionValues;
					
	}
	
	// Get Case Prelim Issues - Drug Claim Overpayment, Drug NDC Error, Drug Quantity Error
	public CaseIndValues getPreliminaryIssues(String prmNode){
		
		CaseIndValues caseIndPrelimIssueValues = new CaseIndValues();
		// prmNode input parm to spec - part of Case ID on the Batch Case Entry screen (CA_LU_PRELIM_ISSUE_T) 
		Specification<CaLuPrelimIssueT> finalPrelimIssueSpecs = CaseValuesSpecification.findPrelimIssueSpecification(prmNode);
		List<CaLuPrelimIssueT> caLuPrelimIssueTList = caLuPrelimIssueRepository.findAll(finalPrelimIssueSpecs);
			//		List<CaseValuesDTO> caLuPrelimIssueDtoList = casePrelimStatusMapper.converToCaseValuesDTOs(caLuPrelimIssueTList);
		List<CaseValuesDTO> caLuPrelimIssueDtoList = new ArrayList<>();
		for (CaLuPrelimIssueT caLuPrelimIssueT : caLuPrelimIssueTList) {
			 CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			 caseValuesDTO.setCode(caLuPrelimIssueT.getIssueCd());
			 caseValuesDTO.setDescription(caLuPrelimIssueT.getIssueDesc());
			 caseValuesDTO.setFilter(caLuPrelimIssueT.getIssueFilter());
			 caLuPrelimIssueDtoList.add(caseValuesDTO);
		 }			 
		 caseIndPrelimIssueValues.setCaseValidValue(CaseEntryConstants.CASE_ISSUE);
		 caseIndPrelimIssueValues.setCaseValues(caLuPrelimIssueDtoList);
					 
		 return caseIndPrelimIssueValues;
		 
	}
	
	// Get Case Fullscale Statuses
	public CaseIndValues getFullscaleStatusValues(String prmNode){

		CaseIndValues caseIndFullscaleStatusValues = new CaseIndValues();
		// prmNode input parm to spec - part of Case ID on the Batch Case Entry screen (CA_LU_FULLSCALE_STATUS_T) 
		Specification<CaLuFullscaleStatusT> finalFullscaleStatusSpecs = CaseValuesSpecification.findFullscaleStatusSpecification(prmNode);
		List<CaLuFullscaleStatusT> caLuFullscaleStatusTList = caLuFullscaleStatusRepository.findAll(finalFullscaleStatusSpecs);
								
		List<CaseValuesDTO> caLuFullscaleStatusDtoList = new ArrayList<>();
		for (CaLuFullscaleStatusT caLuFullscaleStatusT : caLuFullscaleStatusTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuFullscaleStatusT.getStatusCd());
			caseValuesDTO.setDescription(caLuFullscaleStatusT.getStatusDesc());
			caseValuesDTO.setFilter(caLuFullscaleStatusT.getStatusFilter());
			caLuFullscaleStatusDtoList.add(caseValuesDTO);
		}
		caseIndFullscaleStatusValues.setCaseValidValue(CaseEntryConstants.CASE_STATUS);
		caseIndFullscaleStatusValues.setCaseValues(caLuFullscaleStatusDtoList);
						
		return caseIndFullscaleStatusValues;
	}
	
	// Get Fullscale Investigation Assignment Section codes 
	public CaseIndValues getFullscaleAssignmentSectionCodes(String prmNode){
		
		CaseIndValues caseIndFullscaleSectionValues = new CaseIndValues();
		Specification<CaLuFullscaleSectionT> finalFullscaleSectionSpecs = CaseValuesSpecification.findFullscaleSectionSpecification(prmNode);
		List<CaLuFullscaleSectionT> caLuFullscaleSectionTList = caLuFullscaleSectionRepository.findAll(finalFullscaleSectionSpecs);
		List<CaseValuesDTO> caLuFullscaleSectionDtoList = new ArrayList<>();
		for (CaLuFullscaleSectionT caLuFullscaleSectionT : caLuFullscaleSectionTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuFullscaleSectionT.getSectionCd());
			caseValuesDTO.setDescription(caLuFullscaleSectionT.getSectionDesc());
			caseValuesDTO.setFilter(caLuFullscaleSectionT.getSectionFilter());
			caLuFullscaleSectionDtoList.add(caseValuesDTO);
		}
		caseIndFullscaleSectionValues.setCaseValidValue(CaseEntryConstants.CASE_SECTION);
		caseIndFullscaleSectionValues.setCaseValues(caLuFullscaleSectionDtoList);
		
		return caseIndFullscaleSectionValues;

	}
	
	// Get Case Fullscale Issues - Drug Claim Overpayment, Drug NDC Error, Drug Quantity Error
	public CaseIndValues getFullscaleIssues(String prmNode){
		CaseIndValues caseIndFullscaleIssueValues = new CaseIndValues();
		// sourceFilterIn input parm to spec - part of Case ID on the Batch Case Entry screen (CA_LU_PRM_NODE_T) 
		Specification<CaLuFullscaleIssueT> finalPrelimIssueSpecs = CaseValuesSpecification.findFullscaleIssueSpecification(prmNode);
		List<CaLuFullscaleIssueT> caLuFullscaleIssueTList = caLuFullscaleIssueRepository.findAll(finalPrelimIssueSpecs);
							
		List<CaseValuesDTO> caLuFullscaleIssueDtoList = new ArrayList<>();
		for (CaLuFullscaleIssueT caLuFullscaleIssueT : caLuFullscaleIssueTList) {
			CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
			caseValuesDTO.setCode(caLuFullscaleIssueT.getIssueCd());
			caseValuesDTO.setDescription(caLuFullscaleIssueT.getIssueDesc());
			caseValuesDTO.setFilter(caLuFullscaleIssueT.getIssueFilter());
			caLuFullscaleIssueDtoList.add(caseValuesDTO);
		}
							 
		caseIndFullscaleIssueValues.setCaseValidValue(CaseEntryConstants.CASE_ISSUE);
		caseIndFullscaleIssueValues.setCaseValues(caLuFullscaleIssueDtoList);
							 
		return caseIndFullscaleIssueValues;
	}
	
	// Get Case Analysts
	public CaseIndValues getCaseAnalysts(String prmNode) {
	 
	CaseIndValues caseIndAnalysts = new CaseIndValues();
	Specification<UiUserBase> finalUiUserValuesSpecs = CaseValuesSpecification.findAnalystsSpecification();
		List<UiUserBase> uiUserBaseList = userRepository.findAll(finalUiUserValuesSpecs);
	// Get the Case Group IDs that have access to the node (Case ID)	
	Specification<SeCaseGrpNodeAccess> finalCaseGroupIdsSpecs = CaseValuesSpecification.findCaseGroupsSpecification(prmNode);
		List<SeCaseGrpNodeAccess> seCaseGrpNodeAccessList = seCaseGrpNodeAccessRepository.findAll(finalCaseGroupIdsSpecs);
		List <Integer> caseGrpIdList = new ArrayList<>();
		for (SeCaseGrpNodeAccess seCaseGrpNodeAccess : seCaseGrpNodeAccessList) {
			caseGrpIdList.add(seCaseGrpNodeAccess.getId().getCaseGrpId());
		}
		
		List<CaseValuesDTO> caUserList = new ArrayList<>();
		for (UiUserBase uiUserBase : uiUserBaseList) {
			if(caseGrpIdList.contains(uiUserBase.getSeUsrGrp().getSeCaseGrp().getCaseGrpId())) {
				CaseValuesDTO caseValuesDTO = new CaseValuesDTO();
				caseValuesDTO.setCode(uiUserBase.getUiSystemId());
				caseValuesDTO.setDescription(uiUserBase.getUiFirstName() + " " + uiUserBase.getUiLastName());
				caUserList.add(caseValuesDTO);
			}
		}
	
		caseIndAnalysts.setCaseValidValue("CaseAnalysts");
		caseIndAnalysts.setCaseValues(caUserList);	
		
		return caseIndAnalysts;		 

	}

	/**
	  * this method will create entries in CA_UNIVERSE_BATCH_T table
	  * 
	  * Parameters - CaseBatchDataDTO
	 */
	@Transactional
	public List<CaseBatchResultDTO> addCaseBatch(CaseBatchDataDTO caseBatchDataDTO){
		 List<CaUniverseBatchT> caUniverseBatchTList = new ArrayList<>();
		 LocalDateTime currentSqlDate = LocalDateTime.now(getZoneId());
		 SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(CaseEntryConstants.DATE_FORMAT_MMDDYYYY_S, Locale.ENGLISH);

		 List <BasicClaimData> basicClaimDataList = caseBatchDataDTO.getBasicClaimDataList();
		 
		 double dateDouble;
		 boolean excelDate = checkForExcelDate(basicClaimDataList);
		 if (!excelDate) {
			 simpleDateFormatter = getDateFormat(caseBatchDataDTO);
		 }
		// For CaseStatusDate parse
		 SimpleDateFormat dateFormatter = new SimpleDateFormat(CaseEntryConstants.DATE_FORMAT_YYYYMMDD, Locale.ENGLISH);

		try {
			Integer caseBatchId = 0;
			Integer caseUniverseBatchId= 0;

			// Sort on provider ID as there will be a case for each provider ID
			if (basicClaimDataList.size() > 2) {
				basicClaimDataList.sort(Comparator.comparing(BasicClaimData::getProviderId));
			}
			// Batch ID based on CA_UNIVERSE_T as there may be no batches currently
			caseBatchId = caUniverseBatchRepository.getNextBatchId();
			if (caseBatchId == null) {
				caseBatchId = 1;
			}
			// Users can add multiple batches
			
			for (BasicClaimData basicClaimData : basicClaimDataList) {
				// Case batch ID does not change with change in Provider ID	
				CaUniverseBatchT caUniverseBatchT = new CaUniverseBatchT();
				
				if (caseBatchDataDTO.getUiSystemId() != null) {
					UiUserBase uiUserBase = new UiUserBase();
					uiUserBase.setUiSystemId(caseBatchDataDTO.getUiSystemId());
					caUniverseBatchT.setUiUserBase(uiUserBase);			// Set UiUserBase
				}
				
					caUniverseBatchT.setCtBatchId(caseBatchId);
				
				caUniverseBatchT.setCtBatchDate(currentSqlDate);
				caUniverseBatchT.setHdrClmTcn(basicClaimData.getHdrClaimTCN());
				caUniverseBatchT.setLiNum(basicClaimData.getLiNum());
				if (excelDate) {
				//	log.info("exceldate parse HdrClmTcn HdrClaimPdDt " + basicClaimData.getHdrClaimTCN() + " " + basicClaimData.getHdrClaimPdDt());
					dateDouble = Double.parseDouble(basicClaimData.getHdrClaimPdDt());
					caUniverseBatchT.setHdrClmPdDt(DateUtil.getJavaDate(dateDouble));
				} else {
				//	log.info("simpleDateFormatter HdrClmTcn HdrClaimPdDt " + basicClaimData.getHdrClaimTCN() +  " " + basicClaimData.getHdrClaimPdDt());
					caUniverseBatchT.setHdrClmPdDt(simpleDateFormatter.parse(basicClaimData.getHdrClaimPdDt()));	// Default pattern is yyyy-MM-dd
				}
				
				//caUniverseBatchT.setId(caUniverseBatchTPK);
				caUniverseBatchT.setLiDrugValidClmInd(basicClaimData.getLiDrugValidClmInd());
				
				caUniverseBatchT.setCaseTypeCd(caseBatchDataDTO.getCaseType());
				if(!caUniverseBatchT.getCaseTypeCd().equals(CaseEntryConstants.CASE_TYPE_MEMBER_CD)) {
					caUniverseBatchT.setProvRole(caseBatchDataDTO.getProviderRole());
				} else {
					caUniverseBatchT.setProvRole(null);
				}
				caUniverseBatchT.setCaPrmNodeCd(caseBatchDataDTO.getPrmNodeCd());
				caUniverseBatchT.setCaYearId(Integer.valueOf(caseBatchDataDTO.getCaseYear()));
				caUniverseBatchT.setProjectStatus(caseBatchDataDTO.getProtectStatus());
				if (caseBatchDataDTO.getInvstTypeCd().isEmpty()) {
					caUniverseBatchT.setInvstTypeCd(null);
				} else {
					caUniverseBatchT.setInvstTypeCd(caseBatchDataDTO.getInvstTypeCd());
				}
				if (caseBatchDataDTO.getCaseSourceCd().isEmpty()) {	// Not required
					caUniverseBatchT.setCaseSourceCd(null);
				} else {
					caUniverseBatchT.setCaseSourceCd(caseBatchDataDTO.getCaseSourceCd());
				}
				if (caseBatchDataDTO.getCaseStatus().isEmpty()) {
					caUniverseBatchT.setCaseStatusCd(null);
				} else {
					caUniverseBatchT.setCaseStatusCd(caseBatchDataDTO.getCaseStatus());
				}
				if (!(caseBatchDataDTO.getCaseStatusDate() == null || caseBatchDataDTO.getCaseStatusDate().equals(CaseEntryConstants.EMPTY_STR))) {
					caUniverseBatchT.setCaseStatusDt(dateFormatter.parse(caseBatchDataDTO.getCaseStatusDate()));
				} else {
					caUniverseBatchT.setCaseStatusDt(null);
				}
				if (caseBatchDataDTO.getCaseIssue().isEmpty()) {	// Not required 
					caUniverseBatchT.setIssueCd(null);
				} else {
					caUniverseBatchT.setIssueCd(caseBatchDataDTO.getCaseIssue());
				}
				if (caseBatchDataDTO.getAssignUser().isEmpty()) {
					caUniverseBatchT.setAssignUsr(null);
				} else {
					caUniverseBatchT.setAssignUsr(caseBatchDataDTO.getAssignUser());
				}
				if (caseBatchDataDTO.getAssignSection().isEmpty()) {	// Not required if investigation type not selected
					caUniverseBatchT.setAssignSectionCd(null);
				} else {
					caUniverseBatchT.setAssignSectionCd(caseBatchDataDTO.getAssignSection());
				}
				if (!(caseBatchDataDTO.getAssignDate() == null || caseBatchDataDTO.getAssignDate().equals(CaseEntryConstants.EMPTY_STR))) {
					caUniverseBatchT.setAssignDate(dateFormatter.parse(caseBatchDataDTO.getAssignDate()));
				} else {
					caUniverseBatchT.setAssignDate(null);
				}
				
				caUniverseBatchT.setParticipantId(basicClaimData.getProviderId());

				caUniverseBatchTList.add(caUniverseBatchT);

			//	log.info(" Added Case Batch data Case Type, Batch ID = " + caseBatchDataDTO.getCaseType() + " " + caseBatchId);
			}
			caUniverseBatchRepository.saveAll(caUniverseBatchTList);
			caUniverseBatchRepository.flush();

		} catch (Exception e) {
			log.info(" Exception = " + e.getMessage());
			throw new CaseEntryApiException(e);
		} 	
		
	    return new ArrayList<CaseBatchResultDTO>();
	}	

	public List<CaseBatchResultDTO> getCaseBatchResultList() {
		List <CaseBatchResultDTO> caseBatchResultDTOList = new ArrayList<>();
		Integer batchId = 0;
		Integer cases = 0;
		String providerId = "";
		CaseBatchResultDTO caseBatchResultDTO = null;
		//SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		List<CaUniverseBatchT> caUniverseBatchTList = 
				caUniverseBatchRepository.findAll(Sort.by(Sort.Direction.ASC, "ctBatchId").and(Sort.by(Sort.Direction.ASC, "participantId")));
		for (CaUniverseBatchT caUniverseBatchT : caUniverseBatchTList) {
			if (!batchId.equals(caUniverseBatchT.getCtBatchId())) {
				caseBatchResultDTO = new CaseBatchResultDTO();
				batchId = caUniverseBatchT.getCtBatchId();
				caseBatchResultDTO.setBatchId(String.valueOf(batchId));
				caseBatchResultDTO.setBatchCreator(caUniverseBatchT.getUiUserBase().getUiFirstName() + " " + caUniverseBatchT.getUiUserBase().getUiLastName());
			//	caseBatchResultDTO.setCreateDate(formatter.format(caUniverseBatchT.getId().getCtBatchDate()));
				caseBatchResultDTO.setCreateDate(caUniverseBatchT.getCtBatchDate().format(dateTimeFormatter));
				providerId = "";
				cases = 0;
				caseBatchResultDTOList.add(caseBatchResultDTO);
			}
			if (caseBatchResultDTO != null && !providerId.equals(caUniverseBatchT.getParticipantId())) {
				cases++;
				caseBatchResultDTO.setNumberOfCases(String.valueOf(cases));
				providerId = caUniverseBatchT.getParticipantId();
			}
		}
		return caseBatchResultDTOList;
	 }

	/**
	  * this method will delete selected Case Batch 
	  * 
	  * Parameters - Batch Id
	 */	
	@Transactional
	 public String deleteCaseBatch(Integer batchId){
		 String deleteCaseBatchMessage = CaseEntryConstants.FAIL_MESSAGE;
		 int batchsDeleted = 1;
			log.info(" Delete Case Batch for input ID {}", batchId );
			try {
				batchsDeleted = caUniverseBatchRepository.deleteCaseBatch(batchId);
				if (batchsDeleted > 0) {
					deleteCaseBatchMessage = CaseEntryConstants.SUCCESS_MESSAGE;
				}
			} catch (Exception e) {
				throw new CaseEntryApiException(e);
			}
	        return deleteCaseBatchMessage;
		}
	 
	 private SimpleDateFormat getDateFormat(CaseBatchDataDTO caseBatchDataDTO) {
				 
		 SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(CaseEntryConstants.DATE_FORMAT_MMDDYYYY_S, Locale.ENGLISH);
		 BasicClaimData basicClaimData1 = caseBatchDataDTO.getBasicClaimDataList().get(0);
		 if (basicClaimData1.getHdrClaimPdDt() != null ) {
			if (basicClaimData1.getHdrClaimPdDt().indexOf( '/' ) == -1) { 
				simpleDateFormatter = new SimpleDateFormat(CaseEntryConstants.DATE_FORMAT_MMDDYYYY_D, Locale.ENGLISH);
			}
		 }
		 return simpleDateFormatter;
	 }
	 	 
	// Check if the HdrClmPdDt is formatted as date in Excel input, it comes in as a number
	 public boolean checkForExcelDate(List<BasicClaimData> basicClaimDataList) {
		 boolean excelDate = false;
		 if (basicClaimDataList.get(0).getHdrClaimPdDt() != null ) {
			 String inputDate = basicClaimDataList.get(0).getHdrClaimPdDt();
		//	 log.info("HdrClaimPdDt = " + inputDate );
				//	Double.parseDouble(inputDate);
			 if((inputDate.indexOf( '/' ) == -1) && (inputDate.indexOf( '-' ) == -1)) {
		        excelDate = true;  
		    }
		 }
		 return excelDate;
	 }
	 /** 
		 * This method will get the ZoneId corresponding to the time zone used at the customer site
		 * Parameters: the time zone is stored in an entry in the FADS_CONFIG_T table 
		 */
	private ZoneId getZoneId() {
		ZoneId zid = ZoneId.systemDefault();;
		Optional<FadsConfigT> optional = fadsConfigRepository.findById(CaseEntryConstants.DATE_TIME_ZONE_ID);
		if (optional.isPresent()) {
			FadsConfigT fadsConfigT = optional.get();
			zid = ZoneId.of(fadsConfigT.getOptionValue());	//  the zone ID corresponding to date-time zone used
		}
		return zid;
	} 
	 
}
