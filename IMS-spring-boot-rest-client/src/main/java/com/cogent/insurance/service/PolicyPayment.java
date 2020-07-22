package com.cogent.insurance.service;

import com.cogent.insurance.entity.PolicyPaymentEntity;

import java.util.List;
import java.util.Optional;

public interface PolicyPayment {
  List<PolicyPaymentEntity> list();

  Optional<PolicyPaymentEntity> getById(long id);

  Optional<PolicyPaymentEntity> getByNumber(String name);

  void save(PolicyPaymentEntity policyPaymentEntity);

  void delete(long id);

  boolean existsId(long id);

  boolean existsName(String name);
}
