package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

  RoleEntity findByName(String name);
}
