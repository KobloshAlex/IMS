package com.cogent.insurance.model.request;

import com.cogent.insurance.shared.dto.BranchDto;

import java.util.List;

public class CeoRequestModel {

  private String firstName;
  private String lastName;
  private Integer age;
  private Character sex;
  private String address;
  private String password;
  private String email;
  private List<BranchDto> branches;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<BranchDto> getBranches() {
    return branches;
  }

  public void setBranches(List<BranchDto> branches) {
    this.branches = branches;
  }
}
