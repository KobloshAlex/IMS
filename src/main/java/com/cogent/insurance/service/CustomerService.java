package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CustomerDto;

public interface CustomerService {

  CustomerDto createCustomer(CustomerDto customerDto);

  CustomerDto getUserByUserId(String id);

  CustomerDto updateCustomer(String id, CustomerDto customerDto);
}
