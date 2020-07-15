package com.cogent.insurance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ceo")
public class CeoEntity implements Serializable {

  public static final long serialVersionUID = 123402043851390690L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String ceoId;

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

  @OneToMany(mappedBy = "ceoEntity") // cascade
  private List<BranchEntity> branches;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
      name = "ceo_roles",
      joinColumns = @JoinColumn(name = "ceo_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"))
  private Set<RoleEntity> roles;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCeoId() {
    return ceoId;
  }

  public void setCeoId(String ceoId) {
    this.ceoId = ceoId;
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

  public List<BranchEntity> getBranches() {
    return branches;
  }

  public void setBranches(List<BranchEntity> branches) {
    this.branches = branches;
  }

  public Set<RoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(Set<RoleEntity> roles) {
    this.roles = roles;
  }
}
