package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.AgentEntity;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<AgentEntity, Long> {

  AgentEntity findByEmail(String email);

  AgentEntity findByAgentId(String id);
}
