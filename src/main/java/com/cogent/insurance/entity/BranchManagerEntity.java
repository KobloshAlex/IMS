package com.cogent.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Branch_manager")
public class BranchManagerEntity implements Serializable {

  public static final long serialVersionUID = 7098373220975691264L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String managerId;

  @Column(nullable = false, length = 50)
  private String firstName;

  @Column(nullable = false, length = 50)
  private String lastName;

  @Column(nullable = false, length = 3)
  private Integer age;

  @Column(nullable = false, length = 1)
  private Character sex;

  @Column(nullable = false)
  private String branchAddress;

  @Column(nullable = false)
  private String branchCity;

  @Column(nullable = false)
  private String branchState;

  @Column(nullable = false)
  private String encryptedPassword;

  @Column(nullable = false, length = 120)
  private String email;

  @ManyToOne
  @JoinColumn(name = "branch_id")
  @JsonIgnore
  private BranchEntity branchEntity;

  @OneToMany(mappedBy = "branchManager")
  private List<AgentEntity> agents;

  @OneToMany(mappedBy = "branchManager")
  private List<CustomerPolicyEntity> customerPolicies;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getManagerId() {
    return managerId;
  }

  public void setManagerId(String managerId) {
    this.managerId = managerId;
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

  public List<AgentEntity> getAgents() {
    return agents;
  }

  public void setAgents(List<AgentEntity> agents) {
    this.agents = agents;
  }

  public List<CustomerPolicyEntity> getCustomerPolicies() {
    return customerPolicies;
  }

  public void setCustomerPolicies(List<CustomerPolicyEntity> customerPolicies) {
    this.customerPolicies = customerPolicies;
  }
}
