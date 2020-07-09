package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.BranchManagerEntity;
import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.BranchManagerService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.BranchManagerDto;
import com.cogent.insurance.shared.dto.CustomerDto;
import com.cogent.insurance.shared.repository.BranchManagerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchManagerServiceImpl implements BranchManagerService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(BranchManagerServiceImpl.class);

  private final BranchManagerRepository branchManagerRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public BranchManagerServiceImpl(
      BranchManagerRepository branchManagerRepository, ModelMapper modelMapper, Utils utils) {
    this.branchManagerRepository = branchManagerRepository;
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

    // TODO: 7/7/2020 Add BCrypt from spring security
    branchManagerEntity.setEncryptedPassword("encrypted-password");
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

  private boolean isRequiredFieldEmpty(BranchManagerDto branchManagerDto) {
    return branchManagerDto.getEmail().trim().isEmpty()
        || branchManagerDto.getBranchAddress().trim().isEmpty()
        || branchManagerDto.getBranchCity().trim().isEmpty()
        || branchManagerDto.getBranchState().trim().isEmpty()
        || branchManagerDto.getFirstName().trim().isEmpty()
        || branchManagerDto.getLastName().trim().isEmpty();
  }
}
