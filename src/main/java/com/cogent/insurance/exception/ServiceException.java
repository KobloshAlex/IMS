package com.cogent.insurance.exception;

public class ServiceException extends RuntimeException {

  public static final long serialVersionUID = -4622164269434529787L;

  public ServiceException(String message) {
    super(message);
  }
}
