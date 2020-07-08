package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.dto.CustomerDto;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;

  public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
    this.customerRepository = customerRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {

    final CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

    // TODO: 7/7/2020 Encryption + generate id
    customerEntity.setEncryptedPassword("encrypted-password");
    customerEntity.setCustomerId("generated-id");

    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }
}
