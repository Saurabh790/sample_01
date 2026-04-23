/*
//***********************************************
// Copyright UNITEDHEALTH GROUP CORPORATION 2018.
// This software and documentation contain confidential and
// proprietary information owned by UnitedHealth Group Corporation.
// Unauthorized use and distribution are prohibited.
//***********************************************
*/

package com.optum.fads.caseentry.api.exception;

import org.springframework.http.HttpStatus;

/**
 * Provides details about a type of error.
 * <p>
 * General guidance is that this should be implemented by an Enum. The Enum
 * would then provide a central place for the error details in a
 * microservice/application.
 * </p>
 */
public interface ErrorDetail {

	/**
	 * Gets the error message. The message may contain placeholders '{}' for
	 * formatting.
	 *
	 * @return error message
	 */
	String getMessage();

	/**
	 * Gets the error code.
	 *
	 * @return error code
	 */
	String getCode();

	/**
	 * Gets the HTTP Status to return when this exception is thrown by a controller.
	 *
	 * @return HTTP status
	 */
	HttpStatus getHttpStatus();

}