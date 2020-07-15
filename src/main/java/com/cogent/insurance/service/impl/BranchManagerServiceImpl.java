package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.AgentEntity;
import com.cogent.insurance.entity.BranchManagerEntity;
import com.cogent.insurance.entity.CustomerPolicyEntity;
import com.cogent.insurance.entity.RoleEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.BranchManagerService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.BranchManagerDto;
import com.cogent.insurance.shared.repository.AgentRepository;
import com.cogent.insurance.shared.repository.BranchManagerRepository;
import com.cogent.insurance.shared.repository.CustomerPolicyRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BranchManagerServiceImpl implements BranchManagerService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(BranchManagerServiceImpl.class);

  private final BranchManagerRepository branchManagerRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final AgentRepository agentRepository;
  private final CustomerPolicyRepository customerPolicyRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public BranchManagerServiceImpl(
      BranchManagerRepository branchManagerRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      AgentRepository agentRepository,
      CustomerPolicyRepository customerPolicyRepository,
      ModelMapper modelMapper,
      Utils utils) {
    this.branchManagerRepository = branchManagerRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.agentRepository = agentRepository;
    this.customerPolicyRepository = customerPolicyRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public BranchManagerDto createBranchManager(BranchManagerDto branchManagerDto) {

    if (branchManagerRepository.findByEmail(branchManagerDto.getEmail()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(branchManagerDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());

      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final BranchManagerEntity branchManagerEntity =
        modelMapper.map(branchManagerDto, BranchManagerEntity.class);

    branchManagerEntity.setEncryptedPassword(
        bCryptPasswordEncoder.encode(branchManagerDto.getPassword()));
    branchManagerEntity.setManagerId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_CREATE_RECORD.getMessage());

    return modelMapper.map(
        branchManagerRepository.save(branchManagerEntity), BranchManagerDto.class);
  }

  @Override
  public BranchManagerDto getBranchManagerById(String id) {

    if (branchManagerRepository.findByManagerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(branchManagerRepository.findByManagerId(id), BranchManagerDto.class);
  }

  @Override
  public BranchManagerDto updateBranchManager(String id, BranchManagerDto branchManagerDto) {
    if (branchManagerRepository.findByManagerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchManagerEntity managerEntity = branchManagerRepository.findByManagerId(id);
    managerEntity.setFirstName(branchManagerDto.getFirstName());
    managerEntity.setLastName(branchManagerDto.getLastName());
    managerEntity.setAge(branchManagerDto.getAge());
    managerEntity.setSex(branchManagerDto.getSex());
    managerEntity.setBranchAddress(branchManagerDto.getBranchAddress());
    managerEntity.setBranchCity(branchManagerDto.getBranchCity());
    managerEntity.setBranchState(branchManagerDto.getBranchState());
    managerEntity.setEmail(branchManagerDto.getEmail());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(branchManagerRepository.save(managerEntity), BranchManagerDto.class);
  }

  @Override
  public void deleteBranchManager(String id) {

    if (branchManagerRepository.findByManagerId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    branchManagerRepository.delete(branchManagerRepository.findByManagerId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<BranchManagerDto> getAllBranchManagers() {

    List<BranchManagerDto> returnValue = new ArrayList<>();
    final Iterable<BranchManagerEntity> allBranchManagers = branchManagerRepository.findAll();

    for (BranchManagerEntity branchManager : allBranchManagers) {
      returnValue.add(modelMapper.map(branchManager, BranchManagerDto.class));
    }

    return returnValue;
  }

  @Override
  public void addAgent(String managerId, String agentId) {

    final AgentEntity agentEntity = agentRepository.findByAgentId(agentId);
    if (agentEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_AGENT.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchManagerEntity managerEntity = branchManagerRepository.findByManagerId(managerId);
    if (managerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_MANAGER.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    agentEntity.setBranchManager(managerEntity);
    agentRepository.save(agentEntity);
  }

  @Override
  public void addCustomerPolicy(String managerId, String policyId) {

    final CustomerPolicyEntity policyEntity =
        customerPolicyRepository.findByCustomerPolicyId(policyId);
    if (policyEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_POLICY.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchManagerEntity managerEntity = branchManagerRepository.findByManagerId(managerId);
    if (managerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_MANAGER.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    policyEntity.setBranchManager(managerEntity);
    customerPolicyRepository.save(policyEntity);
  }

  private boolean isRequiredFieldEmpty(BranchManagerDto branchManagerDto) {
    return branchManagerDto.getEmail().trim().isEmpty()
        || branchManagerDto.getBranchAddress().trim().isEmpty()
        || branchManagerDto.getBranchCity().trim().isEmpty()
        || branchManagerDto.getBranchState().trim().isEmpty()
        || branchManagerDto.getFirstName().trim().isEmpty()
        || branchManagerDto.getLastName().trim().isEmpty();
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    final BranchManagerEntity managerEntity = branchManagerRepository.findByEmail(email);

    if (managerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_MANAGER.getMessage());
      throw new UsernameNotFoundException(email);
    }

    Set<GrantedAuthority> authorities = new HashSet<>();
    final Set<RoleEntity> roles = managerEntity.getRoles();
    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return new User(
        managerEntity.getEmail(),
        managerEntity.getEncryptedPassword(),
        true,
        true,
        true,
        true,
        authorities);
  }
}
