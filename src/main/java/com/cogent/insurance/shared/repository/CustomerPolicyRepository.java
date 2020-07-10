package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.CustomerPolicyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerPolicyRepository
    extends PagingAndSortingRepository<CustomerPolicyEntity, Long> {

  CustomerPolicyEntity findByCustomerPolicyId(String id);
}
