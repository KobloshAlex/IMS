package com.cogent.insurance.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler {

  // handle only custom ServiceException
  @ExceptionHandler(value = {ServiceException.class})
  public ResponseEntity<Object> handleServiceException(ServiceException exception) {

    return new ResponseEntity<>(
        new ErrorMessage().setTimestamp(new Date()).setMessage(exception.getMessage()),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // handle all other exception
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<Object> handleAllOtherException(Exception exception) {

    return new ResponseEntity<>(
        new ErrorMessage().setTimestamp(new Date()).setMessage(exception.getMessage()),
        new HttpHeaders(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
