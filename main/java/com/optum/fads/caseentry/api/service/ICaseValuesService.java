package com.optum.fads.caseentry.api.service;

import java.util.List;

import com.optum.fads.caseentry.api.dto.BasicClaimData;
import com.optum.fads.caseentry.api.dto.CaseBatchDataDTO;
import com.optum.fads.caseentry.api.dto.CaseBatchResultDTO;
import com.optum.fads.caseentry.api.dto.CaseIndValues;
import com.optum.fads.caseentry.api.dto.CaseInfo;

/**
 * @author anil wagh 
 * ICaseValuesService
 */
public interface ICaseValuesService {
	
	public CaseInfo getCaseInfo(String userId);
	
	public CaseIndValues getCaseSourceValues(String prmNode);
	
	public List<CaseIndValues> findCaseValues(String prmNode, String invstType);

	
	public List<CaseBatchResultDTO> addCaseBatch(CaseBatchDataDTO caseBatchDataDTO);
	
	public List<CaseBatchResultDTO>  getCaseBatchResultList();
	
	public  String deleteCaseBatch(Integer batchId);
}
