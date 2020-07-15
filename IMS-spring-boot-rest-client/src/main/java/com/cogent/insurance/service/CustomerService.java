package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

  CustomerDto createCustomer(CustomerDto customerDto);

  CustomerDto getCustomerByUserId(String id);

  CustomerDto updateCustomer(String id, CustomerDto customerDto);

  void deleteCustomer(String id);

  List<CustomerDto> getCustomers(int page, int limit);

  void addCustomerPolicy(String customerId, String customerPolicyId);
}
