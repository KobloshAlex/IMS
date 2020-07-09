package com.cogent.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Branch")
public class BranchEntity implements Serializable {

  public static final long serialVersionUID = 1863083404406086029L;

  @Id @GeneratedValue private long id;

  @Column(nullable = false)
  private String branchId;

  @Column(nullable = false, length = 50)
  private String branchName;

  @Column(nullable = false, length = 45)
  private String branchAddress;

  @Column(nullable = false, length = 15)
  private String branchCity;

  @Column(nullable = false, length = 2)
  private String branchState;

  @Column(nullable = false, length = 20)
  private String phone;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getBranchId() {
    return branchId;
  }

  public void setBranchId(String branchId) {
    this.branchId = branchId;
  }

  public String getBranchName() {
    return branchName;
  }

  public void setBranchName(String branchName) {
    this.branchName = branchName;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
}
