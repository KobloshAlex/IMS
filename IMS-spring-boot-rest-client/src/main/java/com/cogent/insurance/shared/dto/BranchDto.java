package com.cogent.insurance.shared.dto;

import java.io.Serializable;
import java.util.List;

public class BranchDto implements Serializable {

  public static final long serialVersionUID = -66139173443147062L;

  private String branchId;
  private String branchName;
  private String branchAddress;
  private String branchCity;
  private String branchState;
  private String phone;
  private CeoDto ceoDto;
  private List<CustomerDto> customersDto;
  private List<BranchManagerDto> branchManagersDto;

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

  public CeoDto getCeoDto() {
    return ceoDto;
  }

  public void setCeoDto(CeoDto ceoDto) {
    this.ceoDto = ceoDto;
  }

  public List<CustomerDto> getCustomersDto() {
    return customersDto;
  }

  public void setCustomersDto(List<CustomerDto> customersDto) {
    this.customersDto = customersDto;
  }

  public List<BranchManagerDto> getBranchManagersDto() {
    return branchManagersDto;
  }

  public void setBranchManagersDto(List<BranchManagerDto> branchManagersDto) {
    this.branchManagersDto = branchManagersDto;
  }
}
