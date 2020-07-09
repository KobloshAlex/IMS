package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.BranchManagerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BranchManagerRepository extends CrudRepository<BranchManagerEntity, Long> {

  BranchManagerEntity findByEmail(String email);
  BranchManagerEntity findByManagerId(String email);

}
