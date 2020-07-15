package com.cogent.insurance.shared.dto;

import com.cogent.insurance.entity.AgentEntity;
import com.cogent.insurance.entity.BranchManagerEntity;
import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.entity.PolicyEntity;

import java.io.Serializable;

public class CustomerPolicyDto implements Serializable {

  public static final long serialVersionUID = -5099710898069921997L;

  private String customerPolicyId;
  private String premiumType;
  private String nomineeName;
  private String relational;
  private Double agentCommissionAmount;
  private Boolean termConditions;
  private BranchManagerEntity branchManager;
  private AgentEntity agentEntity;
  private CustomerEntity customerEntity;
  private PolicyEntity policyEntity;

  public String getCustomerPolicyId() {
    return customerPolicyId;
  }

  public void setCustomerPolicyId(String customerPolicyId) {
    this.customerPolicyId = customerPolicyId;
  }

  public String getPremiumType() {
    return premiumType;
  }

  public void setPremiumType(String premiumType) {
    this.premiumType = premiumType;
  }

  public String getNomineeName() {
    return nomineeName;
  }

  public void setNomineeName(String nomineeName) {
    this.nomineeName = nomineeName;
  }

  public String getRelational() {
    return relational;
  }

  public void setRelational(String relational) {
    this.relational = relational;
  }

  public Double getAgentCommissionAmount() {
    return agentCommissionAmount;
  }

  public void setAgentCommissionAmount(Double agentCommissionAmount) {
    this.agentCommissionAmount = agentCommissionAmount;
  }

  public Boolean getTermConditions() {
    return termConditions;
  }

  public void setTermConditions(Boolean termConditions) {
    this.termConditions = termConditions;
  }

  public BranchManagerEntity getBranchManager() {
    return branchManager;
  }

  public void setBranchManager(BranchManagerEntity branchManager) {
    this.branchManager = branchManager;
  }

  public AgentEntity getAgentEntity() {
    return agentEntity;
  }

  public void setAgentEntity(AgentEntity agentEntity) {
    this.agentEntity = agentEntity;
  }

  public CustomerEntity getCustomerEntity() {
    return customerEntity;
  }

  public void setCustomerEntity(CustomerEntity customerEntity) {
    this.customerEntity = customerEntity;
  }

  public PolicyEntity getPolicyEntity() {
    return policyEntity;
  }

  public void setPolicyEntity(PolicyEntity policyEntity) {
    this.policyEntity = policyEntity;
  }
}
