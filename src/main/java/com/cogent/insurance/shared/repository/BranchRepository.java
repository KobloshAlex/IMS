package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.BranchEntity;
import org.springframework.data.repository.CrudRepository;

public interface BranchRepository extends CrudRepository<BranchEntity, Long> {

  BranchEntity findByBranchId(String id);
}
