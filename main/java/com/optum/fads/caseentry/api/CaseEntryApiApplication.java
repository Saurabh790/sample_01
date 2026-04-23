/*
//***********************************************
// Copyright UNITEDHEALTH GROUP CORPORATION 2018.
// This software and documentation contain confidential and
// proprietary information owned by UnitedHealth Group Corporation.
// Unauthorized use and distribution are prohibited.
//***********************************************
*/

package com.optum.fads.caseentry.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Entry point to the cloud scaffolding boiler plate - Spring Boot
 * 
 * @author AUTHOR, AUTHOR_EMAIL
 * @version VERSION
 */
@SpringBootApplication
public class CaseEntryApiApplication {
    /**
     * Main method for cloud scaffolding boiler plate. Sends metadata to discovery
     * service.
     */
    public static void main(String[] args) {
        SpringApplication.run(CaseEntryApiApplication.class, args);
    }
}
