package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CustomerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {

  CustomerDto createCustomer(CustomerDto customerDto);

  CustomerDto getCustomerByUserId(String id);

  CustomerDto updateCustomer(String id, CustomerDto customerDto);

  void deleteCustomer(String id);

  List<CustomerDto> getCustomers(int page, int limit);

  void addCustomerPolicy(String customerId, String customerPolicyId);

  CustomerDto getCustomer(String email);
}
