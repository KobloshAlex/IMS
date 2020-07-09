package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.PolicyEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.PolicyService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.PolicyDto;
import com.cogent.insurance.shared.repository.PolicyRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolicyServiceImpl implements PolicyService {

  private static final int ID_LENGTH = 5;

  private final Logger logger = LoggerFactory.getLogger(PolicyServiceImpl.class);

  private final PolicyRepository policyRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public PolicyServiceImpl(
      PolicyRepository policyRepository, ModelMapper modelMapper, Utils utils) {
    this.policyRepository = policyRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public PolicyDto createPolicy(PolicyDto policyDto) {

    if (policyRepository.findByPolicyId(policyDto.getPolicyId()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(policyDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());
      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final PolicyEntity policyEntity = modelMapper.map(policyDto, PolicyEntity.class);
    policyEntity.setPolicyId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_CREATE_RECORD.getMessage());

    return modelMapper.map(policyRepository.save(policyEntity), PolicyDto.class);
  }

  @Override
  public PolicyDto getPolicyById(String id) {

    if (policyRepository.findByPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(policyRepository.findByPolicyId(id), PolicyDto.class);
  }

  @Override
  public PolicyDto updatePolicy(String id, PolicyDto policyDto) {

    if (policyRepository.findByPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final PolicyEntity policyEntity = policyRepository.findByPolicyId(id);
    policyEntity.setPolicyName(policyDto.getPolicyName());
    policyEntity.setDate(policyDto.getDate());
    policyEntity.setMaturityAmount(policyDto.getMaturityAmount());
    policyEntity.setPolicyAmount(policyDto.getPolicyAmount());
    policyEntity.setPolicyInterest(policyDto.getPolicyInterest());
    policyEntity.setPolicyTerm(policyDto.getPolicyTerm());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(policyRepository.save(policyEntity), PolicyDto.class);
  }

  @Override
  public void deletePolicy(String id) {
    if (policyRepository.findByPolicyId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    policyRepository.delete(policyRepository.findByPolicyId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<PolicyDto> getAllPolicies() {

    List<PolicyDto> returnValue = new ArrayList<>();
    final Iterable<PolicyEntity> policyEntities = policyRepository.findAll();

    for (PolicyEntity policy : policyEntities) {
      returnValue.add(modelMapper.map(policy, PolicyDto.class));
    }

    return returnValue;
  }

  private boolean isRequiredFieldEmpty(PolicyDto policyDto) {
    return policyDto.getPolicyName().trim().isEmpty()
        || policyDto.getPolicyType().trim().isEmpty()
        || policyDto.getDate().toString().trim().isEmpty()
        || policyDto.getMaturityAmount().toString().trim().isEmpty()
        || policyDto.getPolicyTerm().toString().trim().isEmpty()
        || policyDto.getPolicyInterest().toString().trim().isEmpty();
  }
}
