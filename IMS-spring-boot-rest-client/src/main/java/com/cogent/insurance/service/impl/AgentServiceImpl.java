package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.AgentEntity;
import com.cogent.insurance.entity.CustomerPolicyEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.AgentService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.AgentDto;
import com.cogent.insurance.shared.repository.AgentRepository;
import com.cogent.insurance.shared.repository.CustomerPolicyRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(AgentServiceImpl.class);

  private final AgentRepository agentRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final CustomerPolicyRepository customerPolicyRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public AgentServiceImpl(
      AgentRepository agentRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      CustomerPolicyRepository customerPolicyRepository,
      ModelMapper modelMapper,
      Utils utils) {
    this.agentRepository = agentRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.customerPolicyRepository = customerPolicyRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public AgentDto createAgent(AgentDto agentDto) {

    if (agentRepository.findByEmail(agentDto.getEmail()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(agentDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());

      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final AgentEntity agentEntity = modelMapper.map(agentDto, AgentEntity.class);

    agentEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(agentDto.getPassword()));
    agentEntity.setAgentId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_CREATE_RECORD.getMessage());

    return modelMapper.map(agentRepository.save(agentEntity), AgentDto.class);
  }

  @Override
  public AgentDto getAgentById(String id) {

    if (agentRepository.findByAgentId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(agentRepository.findByAgentId(id), AgentDto.class);
  }

  @Override
  public AgentDto updateAgent(String id, AgentDto agentDto) {

    if (agentRepository.findByAgentId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final AgentEntity agentEntity = agentRepository.findByAgentId(id);
    agentEntity.setFirstName(agentDto.getFirstName());
    agentEntity.setLastName(agentDto.getLastName());
    agentEntity.setAge(agentDto.getAge());
    agentEntity.setSex(agentDto.getSex());
    agentEntity.setBranchAddress(agentDto.getBranchAddress());
    agentEntity.setBranchCity(agentDto.getBranchCity());
    agentEntity.setBranchState(agentDto.getBranchState());
    agentEntity.setEmail(agentDto.getEmail());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(agentRepository.save(agentEntity), AgentDto.class);
  }

  @Override
  public void deleteAgent(String id) {

    if (agentRepository.findByAgentId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    agentRepository.delete(agentRepository.findByAgentId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<AgentDto> getAllAgents() {

    List<AgentDto> returnValue = new ArrayList<>();
    final Iterable<AgentEntity> agentEntities = agentRepository.findAll();

    for (AgentEntity agent : agentEntities) {
      returnValue.add(modelMapper.map(agent, AgentDto.class));
    }

    return returnValue;
  }

  @Override
  public void addCustomerPolicy(String agentId, String customerPolicyId) {

    final CustomerPolicyEntity policyEntity =
        customerPolicyRepository.findByCustomerPolicyId(customerPolicyId);
    if (policyEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_POLICY.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final AgentEntity agentEntity = agentRepository.findByAgentId(agentId);
    if (agentEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_AGENT.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    policyEntity.setAgentEntity(agentEntity);
    customerPolicyRepository.save(policyEntity);
  }

  private boolean isRequiredFieldEmpty(AgentDto agentDto) {
    return agentDto.getEmail().trim().isEmpty()
        || agentDto.getBranchAddress().trim().isEmpty()
        || agentDto.getBranchCity().trim().isEmpty()
        || agentDto.getBranchState().trim().isEmpty()
        || agentDto.getFirstName().trim().isEmpty()
        || agentDto.getLastName().trim().isEmpty();
  }
}
