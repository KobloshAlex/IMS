package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.CeoEntity;
import org.springframework.data.repository.CrudRepository;

public interface CeoRepository extends CrudRepository<CeoEntity, Long> {

  CeoEntity findByEmail(String email);

  CeoEntity findByCeoId(String id);
}
