package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.UserRequestModel;
import com.cogent.insurance.model.request.login.UserRequestLoginModel;
import com.cogent.insurance.model.response.UserResponseModel;
import com.cogent.insurance.service.UserService;
import com.cogent.insurance.shared.dto.UserDto;
import org.modelmapper.ModelMapper;
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
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private static final String ID_PATH = "/{id}";

  private final UserService userService;
  private final ModelMapper modelMapper;

  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(path = ID_PATH)
  public UserResponseModel getUser(@PathVariable String id) {

    return modelMapper.map(userService.getUserById(id), UserResponseModel.class);
  }

  @PostMapping
  public UserResponseModel createUser(@RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    return modelMapper.map(userService.createUser(userDto), UserResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public UserResponseModel updateUser(
      @PathVariable String id, @RequestBody UserRequestModel userRequestModel) {

    final UserDto userDto = modelMapper.map(userRequestModel, UserDto.class);

    return modelMapper.map(userService.updateUser(id, userDto), UserResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public UserDto deleteUser(@PathVariable String id) {

    final UserDto returnValue = userService.getUserById(id);
    userService.deleteUser(id);

    return returnValue;
  }

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
