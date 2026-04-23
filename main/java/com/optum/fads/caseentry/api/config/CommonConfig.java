/*
//***********************************************
// Copyright UNITEDHEALTH GROUP CORPORATION 2018.
// This software and documentation contain confidential and
// proprietary information owned by UnitedHealth Group Corporation.
// Unauthorized use and distribution are prohibited.
//***********************************************
*/

package com.optum.fads.caseentry.api.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * The CommonConfig holds spring based configuration
 */
@Configuration
public class CommonConfig {

    /**
     * Model Mapper for mapping objects
     *
     * @return modelMapper
     */
    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public ModelMapper modelMapper() {
        final var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
