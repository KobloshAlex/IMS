package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.UserEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.UserService;
import com.cogent.insurance.shared.LoggerMessages;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.dto.UserDto;
import com.cogent.insurance.shared.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  private static final int ID_LENGTH = 20;

  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final ModelMapper modelMapper;
  private final Utils utils;

  public UserServiceImpl(
      UserRepository userRepository,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      ModelMapper modelMapper,
      Utils utils) {
    this.userRepository = userRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.modelMapper = modelMapper;
    this.utils = utils;
  }

  @Override
  public UserDto createUser(UserDto userDto) {

    if (userRepository.findByEmail(userDto.getEmail()) != null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());

    } else if (isRequiredFieldEmpty(userDto)) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());

      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
    }

    final UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);

    userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
    userEntity.setUserId(utils.generateId(ID_LENGTH));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName() + LoggerMessages.SUCCESS_CREATE_RECORD);

    return modelMapper.map(userRepository.save(userEntity), UserDto.class);
  }

  @Override
  public UserDto getUserById(String id) {

    if (userRepository.findByUserId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    return modelMapper.map(userRepository.findByUserId(id), UserDto.class);
  }

  @Override
  public UserDto updateUser(String id, UserDto userDto) {

    if (userRepository.findByUserId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    UserEntity userEntity = userRepository.findByUserId(id);
    userEntity.setFirstName(userDto.getFirstName());
    userEntity.setLastName(userDto.getLastName());
    userEntity.setEmail(userDto.getEmail());

    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
    return modelMapper.map(userRepository.save(userEntity), UserDto.class);
  }

  @Override
  public void deleteUser(String id) {
    if (userRepository.findByUserId(id) == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD.getMessage());
      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
    }

    userRepository.delete(userRepository.findByUserId(id));
    logger.info(
        new Throwable().getStackTrace()[0].getMethodName()
            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
  }

  @Override
  public List<UserDto> getUsers(int page, int limit) {
    // start pagination from page=1
    if (page > 0) {
      page -= 1;
    }

    List<UserDto> returnValue = new ArrayList<>();

    List<UserEntity> users = userRepository.findAll(PageRequest.of(page, limit)).getContent();

    for (UserEntity user : users) {
      returnValue.add(modelMapper.map(user, UserDto.class));
    }

    return returnValue;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    final UserEntity userEntity = userRepository.findByEmail(email);

    if (userEntity == null) {
      logger.error(
          new Throwable().getStackTrace()[0].getMethodName()
              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
      throw new UsernameNotFoundException(email);
    }

    return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
  }

  private boolean isRequiredFieldEmpty(UserDto userDto) {
    return userDto.getEmail().trim().isEmpty()
        || userDto.getPassword().trim().isEmpty()
        || userDto.getFirstName().trim().isEmpty()
        || userDto.getLastName().trim().isEmpty();
  }
}
