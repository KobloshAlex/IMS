package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

  UserDto createUser(UserDto userDto);

  UserDto getUserById(String id);

  UserDto updateUser(String id, UserDto userDto);

  void deleteUser(String id);

  List<UserDto> getUsers(int page, int limit);

  UserDto getUser(String email);
}
