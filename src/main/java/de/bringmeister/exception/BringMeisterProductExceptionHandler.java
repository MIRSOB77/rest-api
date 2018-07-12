package de.bringmeister.exception;

import de.bringmeister.web.api.ApiErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class BringMeisterProductExceptionHandler {
    HttpHeaders headers = new HttpHeaders();

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundEcxeptiom.class)
    public ResponseEntity<ApiErrorMessage> handleException(EntityNotFoundEcxeptiom productEcxeptiom){
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return new ResponseEntity<ApiErrorMessage>(new ApiErrorMessage(productEcxeptiom.getMessage()), headers, HttpStatus.NOT_FOUND);
    }
}
