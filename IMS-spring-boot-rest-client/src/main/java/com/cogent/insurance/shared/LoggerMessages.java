package com.cogent.insurance.shared;

public enum LoggerMessages {
  FAIL_CREATE_RECORD(" cannot create record. Record with the same email exists"),
  MISSING_REQUIRED_FIELD(" Missing required field"),
  FAIL_GET_RECORD(" record was not found. ID doesnt match"),
  FAIL_GET_RECORD_CEO(" CEO record was not found. ID doesnt match"),
  FAIL_GET_RECORD_AGENT(" Agent record was not found. ID doesnt match"),
  FAIL_GET_RECORD_POLICY(" Customer Policy record was not found. ID doesnt match"),
  FAIL_GET_RECORD_MANAGER(" Branch Manager record was not found. ID doesnt match"),
  FAIL_GET_RECORD_CUSTOMER(" Customer record was not found. ID doesnt match"),
  FAIL_GET_RECORD_BRANCH(" Branch record was not found. ID doesnt match"),
  SUCCESS_UPDATE_RECORD(" record was updated successfully"),
  SUCCESS_DELETE_RECORD(" record was deleted successfully"),
  SUCCESS_CREATE_RECORD(" new record was created successfully");

  private String errorMessage;

  LoggerMessages(String loggerMessage) {
    this.errorMessage = loggerMessage;
  }

  public String getMessage() {
    return errorMessage;
  }

  public void setMessage(String loggerMessage) {
    this.errorMessage = loggerMessage;
  }
}
