package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.PolicyPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyPaymentRepository extends JpaRepository<PolicyPaymentEntity, Long> {

  Optional<PolicyPaymentEntity> findByType(String name);

  boolean existsByType(String name);
}
