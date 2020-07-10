package com.cogent.insurance.shared.dto;

import com.cogent.insurance.entity.BranchManagerEntity;
import com.cogent.insurance.entity.CustomerPolicyEntity;

import java.io.Serializable;
import java.util.List;

public class AgentDto implements Serializable {

  public static final long serialVersionUID = 4952853637430502952L;

  private long id;
  private String agentId;
  private String firstName;
  private String lastName;
  private Integer age;
  private Character sex;
  private String branchAddress;
  private String branchCity;
  private String branchState;
  private String password;
  private String encryptedPassword;
  private String email;
  private BranchManagerEntity branchManager;
  private List<CustomerPolicyEntity> customerPolicies;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getAgentId() {
    return agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public BranchManagerEntity getBranchManager() {
    return branchManager;
  }

  public void setBranchManager(BranchManagerEntity branchManager) {
    this.branchManager = branchManager;
  }

  public List<CustomerPolicyEntity> getCustomerPolicies() {
    return customerPolicies;
  }

  public void setCustomerPolicies(List<CustomerPolicyEntity> customerPolicies) {
    this.customerPolicies = customerPolicies;
  }
}
