package com.optum.fads.caseentry.api.exception;

import com.optum.fads.caseentry.api.dto.BasicClaimData;
import com.optum.fads.caseentry.api.dto.CaseBatchDataDTO;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CaseEntryExceptionHandler {
/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidArgument(MethodArgumentNotValidException exception)
    {
        Map<String,String> errorMap=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->
        {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        return new HashMap<>();
    }
*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<BasicClaimData> handleInvalidArgument(MethodArgumentNotValidException exception)
    {
        List<BasicClaimData> invalidData= new ArrayList<BasicClaimData>();
        invalidData.add(BasicClaimData.builder().hdrClaimTCN("").liNum("").providerId("").build());
    /*    if(exception.getBindingResult().hasErrors()) {
            CaseBatchDataDTO caseBatchDataDTO = (CaseBatchDataDTO) exception.getBindingResult().getTarget();
            invalidData= caseBatchDataDTO.getBasicClaimDataList();
        }*/

        return invalidData;
    }}
