package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

  UserEntity findByEmail(String email);

  UserEntity findByUserId(String id);
}
