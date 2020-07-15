package com.cogent.insurance.exception;

import java.util.Date;

public class ErrorMessage {
  private Date timestamp;
  private String message;
  private String stackTrace;

  public ErrorMessage() {}

  public Date getTimestamp() {
    return timestamp;
  }

  public ErrorMessage setTimestamp(Date timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  public String getMessage() {
    return message;
  }

  public ErrorMessage setMessage(String message) {
    this.message = message;
    return this;
  }

  public String getStackTrace() {
    return stackTrace;
  }

  public ErrorMessage setStackTrace(String stackTrace) {
    this.stackTrace = stackTrace;
    return this;
  }
}
