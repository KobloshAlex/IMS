package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.BranchEntity;
import com.cogent.insurance.entity.BranchManagerEntity;
import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.BranchService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.BranchDto;
import com.cogent.insurance.shared.repository.BranchManagerRepository;
import com.cogent.insurance.shared.repository.BranchRepository;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

  private static final int ID_LENGTH = 5;

  private final Logger logger = LoggerFactory.getLogger(BranchServiceImpl.class);

  private final BranchRepository branchRepository;
  private final CustomerRepository customerRepository;
  private final BranchManagerRepository branchManagerRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public BranchServiceImpl(
      BranchRepository branchRepository,
      CustomerRepository customerRepository,
      BranchManagerRepository branchManagerRepository,
      ModelMapper modelMapper,
      Utils utils) {
    this.branchRepository = branchRepository;
    this.customerRepository = customerRepository;
    this.branchManagerRepository = branchManagerRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public BranchDto createBranch(BranchDto branchDto) {

    if (branchRepository.findByBranchId(branchDto.getBranchId()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(branchDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());
      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final BranchEntity branchEntity = modelMapper.map(branchDto, BranchEntity.class);
    branchEntity.setBranchId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_CREATE_RECORD.getMessage());

    return modelMapper.map(branchRepository.save(branchEntity), BranchDto.class);
  }

  @Override
  public BranchDto getBranchById(String id) {

    if (branchRepository.findByBranchId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(branchRepository.findByBranchId(id), BranchDto.class);
  }

  @Override
  public BranchDto updateBranch(String id, BranchDto branchDto) {

    if (branchRepository.findByBranchId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchEntity branchEntity = branchRepository.findByBranchId(id);
    branchEntity.setBranchName(branchDto.getBranchName());
    branchEntity.setBranchAddress(branchDto.getBranchAddress());
    branchEntity.setBranchCity(branchDto.getBranchCity());
    branchEntity.setBranchState(branchDto.getBranchState());
    branchEntity.setPhone(branchDto.getPhone());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(branchRepository.save(branchEntity), BranchDto.class);
  }

  @Override
  public void deleteBranch(String id) {

    if (branchRepository.findByBranchId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    branchRepository.delete(branchRepository.findByBranchId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<BranchDto> getAllBranches() {

    List<BranchDto> returnValue = new ArrayList<>();
    final Iterable<BranchEntity> allBranches = branchRepository.findAll();

    for (BranchEntity branch : allBranches) {
      returnValue.add(modelMapper.map(branch, BranchDto.class));
    }

    return returnValue;
  }

  @Override
  public void addBranchManager(String branchId, String branchManagerId) {

    final BranchManagerEntity branchManagerEntity =
        branchManagerRepository.findByManagerId(branchManagerId);
    if (branchManagerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_MANAGER.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchEntity branchEntity = branchRepository.findByBranchId(branchId);
    if (branchEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_BRANCH.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    branchManagerEntity.setBranchEntity(branchEntity);
    branchManagerRepository.save(branchManagerEntity);
  }

  @Override
  public void addCustomer(String branchId, String customerId) {
    final CustomerEntity customerEntity = customerRepository.findByCustomerId(customerId);
    if (customerEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    final BranchEntity branchEntity = branchRepository.findByBranchId(branchId);
    if (branchEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_BRANCH.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    customerEntity.setBranchEntity(branchEntity);
    customerRepository.save(customerEntity);
  }

  private boolean isRequiredFieldEmpty(BranchDto branchDto) {
    return branchDto.getBranchName().trim().isEmpty()
        || branchDto.getBranchAddress().trim().isEmpty()
        || branchDto.getBranchCity().trim().isEmpty()
        || branchDto.getBranchState().trim().isEmpty()
        || branchDto.getPhone().trim().isEmpty();
  }
}
