package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.CustomerEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<CustomerEntity, Long> {

  CustomerEntity findByEmail(String email);

  CustomerEntity findByCustomerId(String id);
}
