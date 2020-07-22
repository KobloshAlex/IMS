package com.cogent.insurance.service.impl;


import com.cogent.insurance.entity.PolicyPaymentEntity;
import com.cogent.insurance.service.PolicyPayment;
import com.cogent.insurance.shared.repository.PolicyPaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyPaymentImpl implements PolicyPayment {

  final PolicyPaymentRepository policyPaymentRepository;

  public PolicyPaymentImpl(PolicyPaymentRepository policyPaymentRepository) {
    this.policyPaymentRepository = policyPaymentRepository;
  }

  @Override
  public List<PolicyPaymentEntity> list() {
    return policyPaymentRepository.findAll();
  }

  @Override
  public Optional<PolicyPaymentEntity> getById(long id) {
    return policyPaymentRepository.findById(id);
  }

  @Override
  public Optional<PolicyPaymentEntity> getByNumber(String name) {
    return policyPaymentRepository.findByType(name);
  }

  @Override
  public void save(PolicyPaymentEntity policyPaymentEntity) {
    policyPaymentRepository.save(policyPaymentEntity);
  }

  @Override
  public void delete(long id) {
    policyPaymentRepository.deleteById(id);
  }

  @Override
  public boolean existsId(long id) {
    return policyPaymentRepository.existsById(id);
  }

  @Override
  public boolean existsName(String name) {
    return policyPaymentRepository.existsByType(name);
  }
}
