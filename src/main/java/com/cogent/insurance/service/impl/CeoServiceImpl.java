package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CeoEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.CeoService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.CeoDto;
import com.cogent.insurance.shared.repository.CeoRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CeoServiceImpl implements CeoService {

  private static final int ID_LENGTH = 5;

  private final Logger logger = LoggerFactory.getLogger(CeoServiceImpl.class);

  private final CeoRepository ceoRepository;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public CeoServiceImpl(CeoRepository ceoRepository, ModelMapper modelMapper, Utils utils) {
    this.ceoRepository = ceoRepository;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public CeoDto createCeo(CeoDto ceoDto) {

    if (ceoRepository.findByEmail(ceoDto.getEmail()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(ceoDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());

      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final CeoEntity ceoEntity = modelMapper.map(ceoDto, CeoEntity.class);

    // TODO: 7/7/2020 Add BCrypt from spring security
    ceoEntity.setEncryptedPassword("encrypted-password");
    ceoEntity.setCeoId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName() + LoggerMessages.SUCCESS_CREATE_RECORD);

    return modelMapper.map(ceoRepository.save(ceoEntity), CeoDto.class);
  }

  @Override
  public CeoDto getCeoById(String id) {

    if (ceoRepository.findByCeoId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(ceoRepository.findByCeoId(id), CeoDto.class);
  }

  @Override
  public CeoDto updateCeo(String id, CeoDto ceoDto) {

    if (ceoRepository.findByCeoId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    CeoEntity ceoEntity = ceoRepository.findByCeoId(id);
    ceoEntity.setFirstName(ceoDto.getFirstName());
    ceoEntity.setLastName(ceoDto.getLastName());
    ceoEntity.setAge(ceoDto.getAge());
    ceoEntity.setSex(ceoDto.getSex());
    ceoEntity.setAddress(ceoDto.getAddress());
    ceoEntity.setEmail(ceoDto.getEmail());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(ceoRepository.save(ceoEntity), CeoDto.class);
  }

  @Override
  public void deleteCeo(String id) {

    if (ceoRepository.findByCeoId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    ceoRepository.delete(ceoRepository.findByCeoId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<CeoDto> getAllCeo() {

    List<CeoDto> returnValue = new ArrayList<>();
    final Iterable<CeoEntity> allCeo = ceoRepository.findAll();

    for (CeoEntity ceo : allCeo) {
      returnValue.add(modelMapper.map(ceo, CeoDto.class));
    }

    return returnValue;
  }

  private boolean isRequiredFieldEmpty(CeoDto ceoDto) {
    return ceoDto.getEmail().trim().isEmpty()
        || ceoDto.getAddress().trim().isEmpty()
        || ceoDto.getFirstName().trim().isEmpty()
        || ceoDto.getLastName().trim().isEmpty();
  }
}
