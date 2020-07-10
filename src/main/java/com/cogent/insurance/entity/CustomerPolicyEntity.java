package com.cogent.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Customer_policy")
public class CustomerPolicyEntity implements Serializable {

  public static final long serialVersionUID = -5099710898069921997L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false, length = 30)
  private String customerPolicyId;

  @Column(nullable = false, length = 30)
  private String premiumType;

  @Column(nullable = false, length = 30)
  private String nomineeName;

  @Column(nullable = false, length = 30)
  private String relational;

  @Column(nullable = false, length = 30)
  private Double agentCommissionAmount;

  @Column(nullable = false, length = 30)
  private Boolean termConditions;

  @ManyToOne
  @JoinColumn(name = "branch_manager_id")
  private BranchManagerEntity branchManager;

  @ManyToOne
  @JoinColumn(name = "agent_id")
  private AgentEntity agentEntity;

  @ManyToOne
  @JoinColumn(name = "customers_id")
  private CustomerEntity customerEntity;

  @ManyToOne
  @JoinColumn(name = "policy_id")
  private PolicyEntity policyEntity;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

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
