package com.cogent.insurance.shared.dto;

import com.cogent.insurance.entity.BranchEntity;
import com.cogent.insurance.entity.CustomerPolicyEntity;

import java.io.Serializable;
import java.util.List;

public class CustomerDto implements Serializable {

  public static final long serialVersionUID = 123402043851390690L;

  private long id;
  private String customerId;
  private String firstName;
  private String lastName;
  private Integer age;
  private Character sex;
  private String address;
  private String password;
  private String encryptedPassword;
  private String email;
  private BranchEntity branchEntity;
  private List<CustomerPolicyEntity> customerPolicies;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
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

  public BranchEntity getBranchEntity() {
    return branchEntity;
  }

  public void setBranchEntity(BranchEntity branchEntity) {
    this.branchEntity = branchEntity;
  }

  public List<CustomerPolicyEntity> getCustomerPolicies() {
    return customerPolicies;
  }

  public void setCustomerPolicies(List<CustomerPolicyEntity> customerPolicies) {
    this.customerPolicies = customerPolicies;
  }
}
