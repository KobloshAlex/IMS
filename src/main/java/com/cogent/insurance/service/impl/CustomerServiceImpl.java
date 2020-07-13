package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.entity.CustomerPolicyEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.CustomerDto;
import com.cogent.insurance.shared.repository.CustomerPolicyRepository;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private final CustomerRepository customerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomerPolicyRepository customerPolicyRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public CustomerServiceImpl(
      CustomerRepository customerRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomerPolicyRepository customerPolicyRepository,
      ModelMapper modelMapper,
      Utils utils) {
    this.customerRepository = customerRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.customerPolicyRepository = customerPolicyRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {

    if (customerRepository.findByEmail(customerDto.getEmail()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(customerDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());

      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final CustomerEntity customerEntity = modelMapper.map(customerDto, CustomerEntity.class);

    customerEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(customerDto.getPassword()));
    customerEntity.setCustomerId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName() + LoggerMessages.SUCCESS_CREATE_RECORD);

    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public CustomerDto getCustomerByUserId(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(customerRepository.findByCustomerId(id), CustomerDto.class);
  }

  @Override
  public CustomerDto updateCustomer(String id, CustomerDto customerDto) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    CustomerEntity customerEntity = customerRepository.findByCustomerId(id);
    customerEntity.setFirstName(customerDto.getFirstName());
    customerEntity.setLastName(customerDto.getLastName());
    customerEntity.setAge(customerDto.getAge());
    customerEntity.setSex(customerDto.getSex());
    customerEntity.setAddress(customerDto.getAddress());
    customerEntity.setEmail(customerDto.getEmail());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(customerRepository.save(customerEntity), CustomerDto.class);
  }

  @Override
  public void deleteCustomer(String id) {

    if (customerRepository.findByCustomerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    customerRepository.delete(customerRepository.findByCustomerId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<CustomerDto> getCustomers(int page, int limit) {

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

  @Override
  public void addCustomerPolicy(String customerId, String customerPolicyId) {

    final CustomerPolicyEntity policyEntity =
        customerPolicyRepository.findByCustomerPolicyId(customerPolicyId);
    if (policyEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_POLICY.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
    if (customerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    policyEntity.setCustomerEntity(customerEntity);
    customerPolicyRepository.save(policyEntity);
  }

  @Override
  public CustomerDto getCustomer(String email) {
    // add CustomerID to JSON header
    final CustomerEntity customerEntity = customerRepository.findByEmail(email);

    if (customerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
      throw new UsernameNotFoundException(email);
    }

    CustomerDto returnValue = new CustomerDto();
    BeanUtils.copyProperties(
        customerEntity,
        returnValue); // using BeanUtil to avoid EAGER loading from CustomerEntity.customerPolicies

    return returnValue;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    final CustomerEntity customerEntity = customerRepository.findByEmail(email);

    if (customerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
      throw new UsernameNotFoundException(email);
    }

    return new User(
        customerEntity.getEmail(), customerEntity.getEncryptedPassword(), new ArrayList<>());
  }

  private boolean isRequiredFieldEmpty(CustomerDto customerDto) {
    return customerDto.getEmail().trim().isEmpty()
        || customerDto.getAddress().trim().isEmpty()
        || customerDto.getFirstName().trim().isEmpty()
        || customerDto.getLastName().trim().isEmpty();
  }
}
