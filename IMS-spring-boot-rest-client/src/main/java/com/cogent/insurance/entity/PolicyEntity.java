package com.cogent.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Policy")
public class PolicyEntity implements Serializable {

  public static final long serialVersionUID = -8669909542219809315L;
  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String policyId;

  @Column(nullable = false, length = 50)
  private String policyName;

  @Column(nullable = false, length = 10)
  private Integer policyTerm;

  @Column(nullable = false, length = 10)
  private Double policyAmount;

  @Column(nullable = false, length = 10)
  private Double policyInterest;

  @Column(nullable = false, length = 10)
  private Double maturityAmount;

  @Column(nullable = false, length = 50)
  private String policyType;

  @OneToMany(mappedBy = "policyEntity")
  private List<CustomerPolicyEntity> customerPolicies;

  private String date;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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

  public List<CustomerPolicyEntity> getCustomerPolicies() {
    return customerPolicies;
  }

  public void setCustomerPolicies(List<CustomerPolicyEntity> customerPolicy) {
    this.customerPolicies = customerPolicy;
  }
}
