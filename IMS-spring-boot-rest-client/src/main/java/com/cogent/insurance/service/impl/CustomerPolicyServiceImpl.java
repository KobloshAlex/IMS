package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CustomerPolicyEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.CustomerPolicyService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.CustomerPolicyDto;
import com.cogent.insurance.shared.repository.CustomerPolicyRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerPolicyServiceImpl implements CustomerPolicyService {

  private static final int ID_LENGTH = 30;

  private final Logger logger = LoggerFactory.getLogger(CustomerPolicyServiceImpl.class);

  private final CustomerPolicyRepository customerPolicyRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public CustomerPolicyServiceImpl(
      CustomerPolicyRepository customerPolicyRepository, ModelMapper modelMapper, Utils utils) {
    this.customerPolicyRepository = customerPolicyRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public CustomerPolicyDto createCustomerPolicy(CustomerPolicyDto customerPolicyDto) {

    if (customerPolicyRepository.findByCustomerPolicyId(customerPolicyDto.getCustomerPolicyId())
        != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(customerPolicyDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());
      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final CustomerPolicyEntity customerPolicyEntity =
        modelMapper.map(customerPolicyDto, CustomerPolicyEntity.class);
    customerPolicyEntity.setCustomerPolicyId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_CREATE_RECORD.getMessage());

    return modelMapper.map(
        customerPolicyRepository.save(customerPolicyEntity), CustomerPolicyDto.class);
  }

  @Override
  public CustomerPolicyDto getCustomerPolicy(String id) {

    if (customerPolicyRepository.findByCustomerPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(
        customerPolicyRepository.findByCustomerPolicyId(id), CustomerPolicyDto.class);
  }

  @Override
  public CustomerPolicyDto updateCustomerPolicy(String id, CustomerPolicyDto customerPolicyDto) {

    if (customerPolicyRepository.findByCustomerPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final CustomerPolicyEntity customerPolicyEntity =
        customerPolicyRepository.findByCustomerPolicyId(id);
    customerPolicyEntity.setAgentCommissionAmount(customerPolicyDto.getAgentCommissionAmount());
    customerPolicyEntity.setNomineeName(customerPolicyDto.getNomineeName());
    customerPolicyEntity.setPremiumType(customerPolicyDto.getPremiumType());
    customerPolicyEntity.setRelational(customerPolicyDto.getRelational());
    customerPolicyEntity.setTermConditions(customerPolicyDto.getTermConditions());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(
        customerPolicyRepository.save(customerPolicyEntity), CustomerPolicyDto.class);
  }

  @Override
  public void deleteCustomerPolicy(String id) {

    if (customerPolicyRepository.findByCustomerPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    customerPolicyRepository.delete(customerPolicyRepository.findByCustomerPolicyId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<CustomerPolicyDto> getAllCustomerPolicies() {

    List<CustomerPolicyDto> returnValue = new ArrayList<>();
    final Iterable<CustomerPolicyEntity> customerPolicies = customerPolicyRepository.findAll();

    for (CustomerPolicyEntity customerPolicy : customerPolicies) {
      returnValue.add(modelMapper.map(customerPolicy, CustomerPolicyDto.class));
    }

    return returnValue;
  }

  private boolean isRequiredFieldEmpty(CustomerPolicyDto customerPolicyDto) {
    return customerPolicyDto.getAgentCommissionAmount().toString().trim().isEmpty()
        || customerPolicyDto.getNomineeName().trim().isEmpty()
        || customerPolicyDto.getPremiumType().trim().isEmpty()
        || customerPolicyDto.getRelational().trim().isEmpty();
  }
}
