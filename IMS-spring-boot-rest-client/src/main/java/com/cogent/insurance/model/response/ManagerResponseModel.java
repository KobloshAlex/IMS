package com.cogent.insurance.model.response;

import com.cogent.insurance.entity.BranchEntity;

public class ManagerResponseModel {

  private String branchManagerId;
  private String firstName;
  private String lastName;
  private Integer age;
  private Character sex;
  private String branchAddress;
  private String branchCity;
  private String branchState;
  private String email;
  private BranchEntity branchEntity;

  public String getBranchManagerId() {
    return branchManagerId;
  }

  public void setBranchManagerId(String branchManagerId) {
    this.branchManagerId = branchManagerId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public Character getSex() {
    return sex;
  }

  public void setSex(Character sex) {
    this.sex = sex;
  }

  public String getBranchAddress() {
    return branchAddress;
  }

  public void setBranchAddress(String branchAddress) {
    this.branchAddress = branchAddress;
  }

  public String getBranchCity() {
    return branchCity;
  }

  public void setBranchCity(String branchCity) {
    this.branchCity = branchCity;
  }

  public String getBranchState() {
    return branchState;
  }

  public void setBranchState(String branchState) {
    this.branchState = branchState;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BranchEntity getBranchEntity() {
    return branchEntity;
  }

  public void setBranchEntity(BranchEntity branchEntity) {
    this.branchEntity = branchEntity;
  }
}
