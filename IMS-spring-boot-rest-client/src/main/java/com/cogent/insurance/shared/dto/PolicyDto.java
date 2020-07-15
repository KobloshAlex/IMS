package com.cogent.insurance.shared.dto;

import com.cogent.insurance.entity.CustomerPolicyEntity;

import java.io.Serializable;
import java.util.List;

public class PolicyDto implements Serializable {

  public static final long serialVersionUID = 5342992363790057086L;

  private String policyId;
  private String policyName;
  private Integer policyTerm;
  private Double policyAmount;
  private Double policyInterest;
  private Double maturityAmount;
  private String policyType;
  private String date; // TODO: 7/9/2020 LocalDate JSON
  private List<CustomerPolicyEntity> customerPolicy;

  public String getPolicyId() {
    return policyId;
  }

  public void setPolicyId(String policyId) {
    this.policyId = policyId;
  }

  public String getPolicyName() {
    return policyName;
  }

  public void setPolicyName(String policyName) {
    this.policyName = policyName;
  }

  public Integer getPolicyTerm() {
    return policyTerm;
  }

  public void setPolicyTerm(Integer policyTerm) {
    this.policyTerm = policyTerm;
  }

  public Double getPolicyAmount() {
    return policyAmount;
  }

  public void setPolicyAmount(Double policyAmount) {
    this.policyAmount = policyAmount;
  }

  public Double getPolicyInterest() {
    return policyInterest;
  }

  public void setPolicyInterest(Double policyInterest) {
    this.policyInterest = policyInterest;
  }

  public Double getMaturityAmount() {
    return maturityAmount;
  }

  public void setMaturityAmount(Double maturityAmount) {
    this.maturityAmount = maturityAmount;
  }

  public String getPolicyType() {
    return policyType;
  }

  public void setPolicyType(String policyType) {
    this.policyType = policyType;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public List<CustomerPolicyEntity> getCustomerPolicy() {
    return customerPolicy;
  }

  public void setCustomerPolicy(List<CustomerPolicyEntity> customerPolicy) {
    this.customerPolicy = customerPolicy;
  }
}
