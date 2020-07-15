package com.cogent.insurance.controller;

import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.model.request.UserRequestModel;
import com.cogent.insurance.model.response.UserResponseModel;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.service.UserService;
import com.cogent.insurance.shared.dto.UserDto;
import com.cogent.insurance.shared.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.cogent.insurance.shared.Roles.ROLE_AGENT;
import static com.cogent.insurance.shared.Roles.ROLE_CEO;
import static com.cogent.insurance.shared.Roles.ROLE_CUSTOMER;
import static com.cogent.insurance.shared.Roles.ROLE_MANAGER;
import static java.util.Collections.singletonList;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private static final String ID_PATH = "/{id}";

  private final UserService userService;
  private final ModelMapper modelMapper;
  private final CustomerRepository customerRepository;

  public UserController(
      UserService userService,
      ModelMapper modelMapper,
      CustomerRepository customerRepository) {
    this.userService = userService;
    this.modelMapper = modelMapper;
    this.customerRepository = customerRepository;
  }

  @Secured({"ROLE_AGENT", "ROLE_MANAGER", "ROLE_CEO"})
  @GetMapping(path = ID_PATH)
  public UserResponseModel getUser(@PathVariable String id) {

    return modelMapper.map(userService.getUserById(id), UserResponseModel.class);
  }

  @Secured({"ROLE_AGENT", "ROLE_MANAGER"})
  @PostMapping("/new-customer")
  public UserResponseModel createUserCustomer(@RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
    userDto.setRoles(new HashSet<>(singletonList(ROLE_CUSTOMER.name())));

    return modelMapper.map(userService.createUser(userDto), UserResponseModel.class);
  }

  @Secured("ROLE_MANAGER")
  @PostMapping("/new-agent")
  public UserResponseModel createUserAgent(@RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
    userDto.setRoles(new HashSet<>(singletonList(ROLE_AGENT.name())));

    return modelMapper.map(userService.createUser(userDto), UserResponseModel.class);
  }

  @Secured("ROLE_CEO")
  @PostMapping("/new-manager")
  public UserResponseModel createUserManager(@RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
    userDto.setRoles(new HashSet<>(singletonList(ROLE_MANAGER.name())));

    return modelMapper.map(userService.createUser(userDto), UserResponseModel.class);
  }

  @Secured("ROLE_CEO")
  @PostMapping("/new-ceo")
  public UserResponseModel createUserCeo(@RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);
    userDto.setRoles(new HashSet<>(singletonList(ROLE_CEO.name())));

    return modelMapper.map(userService.createUser(userDto), UserResponseModel.class);
  }

  @Secured({"ROLE_AGENT", "ROLE_MANAGER"})
  @PutMapping(path = ID_PATH)
  public UserResponseModel updateUser(
      @PathVariable String id, @RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    return modelMapper.map(userService.updateUser(id, userDto), UserResponseModel.class);
  }

  @Secured({"ROLE_CEO", "ROLE_MANAGER"})
  @DeleteMapping(path = ID_PATH)
  public UserDto deleteUser(@PathVariable String id) {

    final UserDto returnValue = userService.getUserById(id);
    userService.deleteUser(id);

    return returnValue;
  }

  @Secured({"ROLE_CEO", "ROLE_MANAGER", "ROLE_AGENT"})
  @GetMapping
  public List<UserResponseModel> getAllUsers(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "5") int limit) {

    List<UserResponseModel> returnValue = new ArrayList<>();
    List<UserDto> users = userService.getUsers(page, limit);

    for (UserDto user : users) {
      returnValue.add(modelMapper.map(user, UserResponseModel.class));
    }

    return returnValue;
  }
}
