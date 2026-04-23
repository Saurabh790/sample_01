package com.optum.fads.caseentry.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CaseEntryApiException  extends RuntimeException {
	private static final long serialVersionUID = 1L;
	 public CaseEntryApiException(Exception e) {
	        super(e.getMessage());
	    }
	 public CaseEntryApiException(String exceptionMessage) {
	        super(exceptionMessage);
	    }
}
