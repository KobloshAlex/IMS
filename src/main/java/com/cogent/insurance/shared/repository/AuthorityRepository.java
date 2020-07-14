package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.AuthorityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {

  AuthorityEntity findByName(String name);
}
