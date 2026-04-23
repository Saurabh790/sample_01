/*
//***********************************************
// Copyright UNITEDHEALTH GROUP CORPORATION 2018.
// This software and documentation contain confidential and
// proprietary information owned by UnitedHealth Group Corporation.
// Unauthorized use and distribution are prohibited.
//***********************************************
*/

package com.optum.fads.caseentry.api.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.optum.fads.caseentry.api.dto.AppUser;
import com.optum.fads.caseentry.api.dto.BasicClaimData;
import com.optum.fads.caseentry.api.dto.CaseBatchDataDTO;
import com.optum.fads.caseentry.api.dto.CaseBatchResultDTO;
import com.optum.fads.caseentry.api.dto.CaseIndValues;
import com.optum.fads.caseentry.api.dto.CaseInfo;
import com.optum.fads.caseentry.api.exception.CaseEntryApiException;
import com.optum.fads.caseentry.api.service.ICaseValuesService;
import com.optum.fads.caseentry.api.common.constants.CaseEntryConstants;

import lombok.extern.slf4j.Slf4j;
/**
 * Controller class containing routes for API business logic.
 *
 */

@RestController
@Validated
@CrossOrigin
@Slf4j
public class CaseValuesController {

    @Autowired
	private ICaseValuesService iCaseValuesService;

    @GetMapping(value = { "/case-info-values" }, headers = "Accept=application/json")
//	@ApiOperation(value = "case-info-values", notes = "This will provide main case information - ID, Year.")

	public CaseInfo  getCaseInfoValues() {
    	String userId = null;
    	try {
    		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				AppUser user = (AppUser) auth.getPrincipal();
				userId = user.getUserSystemId();
			}
		} catch (CaseEntryApiException ex) {
			log.error(ex.getMessage());
		}
    	CaseInfo caseInfo = iCaseValuesService.getCaseInfo(userId);	
		
		 return caseInfo;
	}
    
//    @ApiOperation(value = "case-source-values", notes = "This will provide the case source values .")
	@GetMapping(value = { "/case-source-values/{prmNode}" }, headers = "Accept=application/json")

	public CaseIndValues  getCaseSourceValues( 
//    	@ApiParam(name = "prmNode", value = "PRM Node", required = true) 
    	@PathVariable("prmNode") String prmNode){
    	
		 CaseIndValues caseSourceValuesList = iCaseValuesService.getCaseSourceValues(prmNode);
		
		 return caseSourceValuesList;
	}
    
//	@ApiOperation(value = "case-related-values", notes = "This will provide all the case related values .")
	@GetMapping(value = { "/case-related-values/{prmNode}/{invstType}" }, headers = "Accept=application/json")

	public List<CaseIndValues>  getRelatedValues( 
  //  	@ApiParam(name = "prmNode", value = "PRM Node", required = true)
    		@PathVariable("prmNode") String prmNode,
  //		@ApiParam(name = "invstType", value = "Investigation Type", required = true) 
    		@PathVariable("invstType") String invstType){
    	
		 List<CaseIndValues> caseBasedValuesList =iCaseValuesService.findCaseValues(prmNode, invstType);
		
		 return caseBasedValuesList;
	}

	/**
	 * this method will validate the input; if no errors found, it will add rows to ca_universe_batch_t table
	 * @param - CaseBatchDataDTO
	 * returns invalid claims list if validation fails, else saves the batch cases
	 */
	@PostMapping(value = { "/save-case-batch" })
	public ResponseEntity saveCaseBatch(@RequestBody @Valid CaseBatchDataDTO caseBatchDataDTO) {

		List<CaseBatchResultDTO> caseBatchResultDTOList;
		try {
			Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				AppUser user = (AppUser) auth.getPrincipal();
				caseBatchDataDTO.setUiSystemId(user.getUserSystemId());
			}
			if (caseBatchDataDTO.getBasicClaimDataList().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CaseEntryConstants.EMPTY_CLAIMS_LIST);
			} else {
				caseBatchResultDTOList = iCaseValuesService.addCaseBatch(caseBatchDataDTO);
                return ResponseEntity.status(HttpStatus.OK).body(caseBatchResultDTOList);
			}
		} catch (CaseEntryApiException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured while processing the save case batch request");
		}
		
	}
	@GetMapping(value = { "/case-batch-summary" }, headers = "Accept=application/json")
//	@ApiOperation(value = "case-batch-summary", notes = "This will obtain the case batch summary list.")

	public List<CaseBatchResultDTO>  caseBatchSummary() {
    	
		/**
			 * Retrieves a list of CaseBatchResultDTO objects by invoking the 
			 * getCaseBatchResultList method from the iCaseValuesService.
			 * 
			 * @return A list of CaseBatchResultDTO objects containing batch result details.
			 */
		List<CaseBatchResultDTO> caseBatchResultDTOList = new ArrayList<>();
		try {
			caseBatchResultDTOList = iCaseValuesService.getCaseBatchResultList();
		} catch (Exception ex) {
			log.error("Error occurred while fetching case batch summary: {}", ex.getMessage());
		}
		return caseBatchResultDTOList;
	}

	/**
	 * this method will delete the Case Batch records corresponding to input
	 * Batch ID
	 * 
	 * @param - Batch ID
	 */
	@DeleteMapping(value = "/delete-case-batch/{batchid}")
	public ResponseEntity<String> deleteCaseBatchById(
			@PathVariable(name = "batchid") Integer batchId)

	{
		String caseBatchMessage;
		try {
			caseBatchMessage = iCaseValuesService.deleteCaseBatch(batchId);
		} catch (CaseEntryApiException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exception");
		}
		return ResponseEntity.status(HttpStatus.OK).body(caseBatchMessage);
	}
}
