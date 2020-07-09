package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.CustomerDto;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

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

    // TODO: 7/8/2020 replace with custom exceptions
    if (customerRepository.findByEmail(customerDto.getEmail()) != null) {
      logger.error(
          getMethodName() + " cannot create customer record. Record with the same email exists");
      throw new RuntimeException("Record is already exist");
    }

    final CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

    // TODO: 7/7/2020 Add BCrypt form spring security
    customerEntity.setEncryptedPassword("encrypted-password");
    customerEntity.setCustomerId(utils.generateId(ID_LENGTH));
    logger.info(getMethodName() + "new customer record was created");

    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public CustomerDto getUserByUserId(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(getMethodName() + " customer record was not found. Customer ID doesnt match");
      throw new RuntimeException("Record with provided ID not found");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    return modelMapper.map(customerRepository.findByCustomerId(id), CustomerDto.class);
  }

  @Override
  public CustomerDto updateCustomer(String id, CustomerDto customerDto) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(getMethodName() + " customer record was not found. Customer ID doesnt match");
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

    logger.info(getMethodName() + "new customer record was updated");
    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public void deleteCustomer(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(getMethodName() + " customer record was not found. Customer ID doesnt match");
      throw new RuntimeException("Record with provided ID not found");
      // TODO: 7/8/2020 replace with custom exceptions
    }

    customerRepository.delete(customerRepository.findByCustomerId(id));
    logger.info(getMethodName() + "new customer record was deleted");
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

  private String getMethodName() {
    return new Throwable().getStackTrace()[0].getMethodName();
  }
}
