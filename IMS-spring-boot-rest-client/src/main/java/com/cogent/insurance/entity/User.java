package com.cogent.insurance.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = "username"),
      @UniqueConstraint(columnNames = "email")
    })
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userId;
  private String username;
  private String email;
  private String encryptedPassword;
  private String emailVerificationToken;
  private Boolean emailVerificationStatus;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  public User() {}

  public User(String username, String email, String encryptedPassword, String userId) {
    this.username = username;
    this.email = email;
    this.encryptedPassword = encryptedPassword;
    this.userId = userId;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public void setEncryptedPassword(String password) {
    this.encryptedPassword = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getEmailVerificationToken() {
    return emailVerificationToken;
  }

  public void setEmailVerificationToken(String emailVerificationToken) {
    this.emailVerificationToken = emailVerificationToken;
  }

  public Boolean getEmailVerificationStatus() {
    return emailVerificationStatus;
  }

  public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
    this.emailVerificationStatus = emailVerificationStatus;
  }
}
