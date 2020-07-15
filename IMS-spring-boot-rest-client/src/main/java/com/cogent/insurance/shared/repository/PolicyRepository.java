package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.PolicyEntity;
import org.springframework.data.repository.CrudRepository;

public interface PolicyRepository extends CrudRepository<PolicyEntity, Long> {

  PolicyEntity findByPolicyId(String id);
}
