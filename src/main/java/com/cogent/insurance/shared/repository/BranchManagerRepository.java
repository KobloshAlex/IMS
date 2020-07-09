package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.BranchManagerEntity;
import org.springframework.data.repository.CrudRepository;

public interface BranchManagerRepository extends CrudRepository<BranchManagerEntity, Long> {

  BranchManagerEntity findByEmail(String email);

  BranchManagerEntity findByManagerId(String email);
}
