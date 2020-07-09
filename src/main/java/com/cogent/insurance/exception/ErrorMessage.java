package com.cogent.insurance.exception;

import java.util.Date;

public class ErrorMessage {
  private Date timestamp;
  private String message;

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
}
