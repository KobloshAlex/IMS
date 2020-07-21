// package com.cogent.insurance.service.impl;
//
// import com.cogent.insurance.entity.User;
// import com.cogent.insurance.exception.ErrorMessages;
// import com.cogent.insurance.exception.ServiceException;
// import com.cogent.insurance.security.UserPrincipals;
// import com.cogent.insurance.service.UserService;
// import com.cogent.insurance.shared.LoggerMessages;
// import com.cogent.insurance.shared.Utils;
// import com.cogent.insurance.shared.dto.UserDto;
// import com.cogent.insurance.shared.repository.RoleRepository;
// import com.cogent.insurance.shared.repository.UserRepository;
// import org.modelmapper.ModelMapper;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.BeanUtils;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;
//
// import java.util.ArrayList;
// import java.util.List;
//
// @Service
// public class UserServiceImpl implements UserService {
//
//  private static final int ID_LENGTH = 20;
//
//  private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//
//  private final UserRepository userRepository;
//  private final BCryptPasswordEncoder bCryptPasswordEncoder;
//  private final ModelMapper modelMapper;
//  private final Utils utils;
//  private final RoleRepository roleRepository;
//
//  public UserServiceImpl(
//      UserRepository userRepository,
//      BCryptPasswordEncoder bCryptPasswordEncoder,
//      ModelMapper modelMapper,
//      Utils utils,
//      RoleRepository roleRepository) {
//    this.userRepository = userRepository;
//    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    this.modelMapper = modelMapper;
//    this.utils = utils;
//    this.roleRepository = roleRepository;
//  }
//
//  @Override
//  public UserDto createUser(UserDto userDto) {
//
//    //    if (userRepository.findByEmail(userDto.getEmail()) != null) {
//    //      logger.error(
//    //          new Throwable().getStackTrace()[0].getMethodName()
//    //              + LoggerMessages.FAIL_CREATE_RECORD.getMessage());
//    //      throw new ServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
//    //
//    //    } else if (isRequiredFieldEmpty(userDto)) {
//    //      logger.error(
//    //          new Throwable().getStackTrace()[0].getMethodName()
//    //              + LoggerMessages.MISSING_REQUIRED_FIELD.getMessage());
//    //
//    //      throw new ServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
//    //    }
//    //
//    //    final User user = modelMapper.map(userDto, User.class);
//    //
//    //    user.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
//    //    user.setUserId(utils.generateId(ID_LENGTH));
//    //    Collection<Role> roles = new HashSet<>(); // set roles
//    //    for (String role : userDto.getRoles()) {
//    //      Role roleEntity = roleRepository.findByName(role);
//    //      if (roleEntity != null) {
//    //        roles.add(roleEntity);
//    //      }
//    //    }
//    //    user.setRoles(roles);
//    //
//    //    logger.info(
//    //        new Throwable().getStackTrace()[0].getMethodName() +
//    // LoggerMessages.SUCCESS_CREATE_RECORD);
//
//    return null;
//  }
//
//  @Override
//  public UserDto getUserById(String id) {
//
//    if (userRepository.findByUserId(id) == null) {
//      logger.error(
//          new Throwable().getStackTrace()[0].getMethodName()
//              + LoggerMessages.FAIL_GET_RECORD.getMessage());
//      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
//    }
//
//    return modelMapper.map(userRepository.findByUserId(id), UserDto.class);
//  }
//
//  @Override
//  public UserDto updateUser(String id, UserDto userDto) {
//
//    if (userRepository.findByUserId(id) == null) {
//      logger.error(
//          new Throwable().getStackTrace()[0].getMethodName()
//              + LoggerMessages.FAIL_GET_RECORD.getMessage());
//      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
//    }
//
//    User user = userRepository.findByUserId(id);
//
//    user.setEmail(userDto.getEmail());
//
//    logger.info(
//        new Throwable().getStackTrace()[0].getMethodName()
//            + LoggerMessages.SUCCESS_UPDATE_RECORD.getMessage());
//    return modelMapper.map(userRepository.save(user), UserDto.class);
//  }
//
//  @Override
//  public void deleteUser(String id) {
//    if (userRepository.findByUserId(id) == null) {
//      logger.error(
//          new Throwable().getStackTrace()[0].getMethodName()
//              + LoggerMessages.FAIL_GET_RECORD.getMessage());
//      throw new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage());
//    }
//
//    userRepository.delete(userRepository.findByUserId(id));
//    logger.info(
//        new Throwable().getStackTrace()[0].getMethodName()
//            + LoggerMessages.SUCCESS_DELETE_RECORD.getMessage());
//  }
//
//  @Override
//  public List<UserDto> getUsers(int page, int limit) {
//    // start pagination from page=1
//    if (page > 0) {
//      page -= 1;
//    }
//
//    List<UserDto> returnValue = new ArrayList<>();
//
//    List<User> users = userRepository.findAll(PageRequest.of(page, limit)).getContent();
//
//    for (User user : users) {
//      returnValue.add(modelMapper.map(user, UserDto.class));
//    }
//
//    return returnValue;
//  }
//
//  @Override
//  public UserDto getUser(String email) {
//    // add CustomerID to JSON header
//    final User user = userRepository.findByEmail(email);
//
//    if (user == null) {
//      logger.error(
//          new Throwable().getStackTrace()[0].getMethodName()
//              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
//      throw new UsernameNotFoundException(email);
//    }
//
//    UserDto returnValue = new UserDto();
//    BeanUtils.copyProperties(user, returnValue);
//
//    return returnValue;
//  }
//
//  @Override
//  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//    final User user = userRepository.findByEmail(email);
//
//    if (user == null) {
//      logger.error(
//          new Throwable().getStackTrace()[0].getMethodName()
//              + LoggerMessages.FAIL_GET_RECORD_CUSTOMER.getMessage());
//      throw new UsernameNotFoundException(email);
//    }
//
//    return new UserPrincipals(user);
//  }
//
//  private boolean isRequiredFieldEmpty(UserDto userDto) {
//    return userDto.getEmail().trim().isEmpty()
//        || userDto.getPassword().trim().isEmpty()
//        || userDto.getFirstName().trim().isEmpty()
//        || userDto.getLastName().trim().isEmpty();
//  }
// }
