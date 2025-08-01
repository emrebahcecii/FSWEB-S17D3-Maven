package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleException(ZooException zooException){
        log.error("ZooException occurred: {}", zooException.getMessage());

        ZooErrorResponse zooErrorResponse
                = new ZooErrorResponse(zooException.getHttpStatus().value(), zooException.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, zooException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ZooErrorResponse> handleGeneralException(Exception exception){
        log.error("Unexpected exception occurred: ", exception);

        ZooErrorResponse zooErrorResponse
                = new ZooErrorResponse( HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(zooErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
