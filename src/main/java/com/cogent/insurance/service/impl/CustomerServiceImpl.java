package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.CustomerDto;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final int ID_LENGTH = 20;

  private final CustomerRepository customerRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public CustomerServiceImpl(
      CustomerRepository customerRepository, ModelMapper modelMapper, Utils utils) {
    this.customerRepository = customerRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {

    if (customerRepository.findByEmail(customerDto.getEmail()) != null) {
      throw new RuntimeException("Record is already exist");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    final CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

    // TODO: 7/7/2020 Add BCrypt form spring security
    customerEntity.setEncryptedPassword("encrypted-password");
    customerEntity.setCustomerId(utils.generateId(ID_LENGTH));

    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public CustomerDto getUserByUserId(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      throw new RuntimeException("Record with provided ID not found");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    return modelMapper.map(customerRepository.findByCustomerId(id), CustomerDto.class);
  }

  @Override
  public CustomerDto updateCustomer(String id, CustomerDto customerDto) {

    if (customerRepository.findByCustomerId(id) == null) {
      throw new RuntimeException("Record with provided ID not found");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    CustomerEntity customerEntity = customerRepository.findByCustomerId(id);
    customerEntity.setFirstName(customerDto.getFirstName());
    customerEntity.setLastName(customerDto.getLastName());
    customerEntity.setAge(customerDto.getAge());
    customerEntity.setSex(customerDto.getSex());
    customerEntity.setAddress(customerDto.getAddress());
    customerEntity.setEmail(customerDto.getEmail());

    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public void deleteCustomer(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      throw new RuntimeException("Record with provided ID not found");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    customerRepository.delete(customerRepository.findByCustomerId(id));
  }

  @Override
  public List<CustomerDto> getUsers(int page, int limit) {

    // start pagination from page=1
    if (page > 0) {
      page -= 1;
    }

    List<CustomerDto> returnValue = new ArrayList<>();

    List<CustomerEntity> customers =
        customerRepository.findAll(PageRequest.of(page, limit)).getContent();

    for (CustomerEntity customer : customers) {
      returnValue.add(modelMapper.map(customer, CustomerDto.class));
    }

    return returnValue;
  }
}
