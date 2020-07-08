package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
}
