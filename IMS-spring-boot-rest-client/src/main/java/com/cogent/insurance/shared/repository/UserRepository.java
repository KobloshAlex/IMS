package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  User findByEmail(String email);

  User findByUserId(String id);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  User findUserByEmailVerificationToken(String token);
}
