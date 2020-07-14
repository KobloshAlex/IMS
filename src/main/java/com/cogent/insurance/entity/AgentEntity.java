package com.cogent.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Agent")
public class AgentEntity implements Serializable {

  public static final long serialVersionUID = 9179229876621407229L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String agentId;

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
  @JoinColumn(name = "branch_manager_id")
  @JsonIgnore
  private BranchManagerEntity branchManager;

  @OneToMany(mappedBy = "agentEntity")
  private List<CustomerPolicyEntity> customerPolicies;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
          name = "agents_roles",
          joinColumns = @JoinColumn(name = "agent_id", referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
  private Collection<RoleEntity> roles;

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

  public Collection<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(Collection<RoleEntity> roles) {
    this.roles = roles;
  }
}
