package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CustomerPolicyDto;

import java.util.List;

public interface CustomerPolicyService {

  CustomerPolicyDto createCustomerPolicy(CustomerPolicyDto customerPolicyDto);

  CustomerPolicyDto getCustomerPolicy(String id);

  CustomerPolicyDto updateCustomerPolicy(String id, CustomerPolicyDto customerPolicyDto);

  void deleteCustomerPolicy(String id);

  List<CustomerPolicyDto> getAllCustomerPolicies();
}
