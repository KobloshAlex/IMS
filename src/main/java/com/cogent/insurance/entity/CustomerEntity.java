package com.cogent.insurance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Customers")
public class CustomerEntity implements Serializable {

  public static final long serialVersionUID = 123402043851390690L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String customerId;

  @Column(nullable = false, length = 50)
  private String firstName;

  @Column(nullable = false, length = 50)
  private String lastName;

  @Column(nullable = false, length = 3)
  private Integer age;

  @Column(nullable = false, length = 1)
  private Character sex;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String encryptedPassword;

  @Column(nullable = false, length = 120)
  private String email;

  @ManyToOne
  @JoinColumn(name = "branch_id")
  @JsonIgnore
  private BranchEntity branchEntity;

  @OneToMany(mappedBy = "customerEntity")
  private List<CustomerPolicyEntity> customerPolicies;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
      name = "customers_roles",
      joinColumns = @JoinColumn(name = "customers_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
  private Set<RoleEntity> roles;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEncryptedPassword() {
    return encryptedPassword;
  }

  public void setEncryptedPassword(String encryptedPassword) {
    this.encryptedPassword = encryptedPassword;
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

  public Set<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
  }
}
