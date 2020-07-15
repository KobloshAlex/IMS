package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.AgentDto;

import java.util.List;

public interface AgentService {

  AgentDto createAgent(AgentDto agentDto);

  AgentDto getAgentById(String id);

  AgentDto updateAgent(String id, AgentDto agentDto);

  void deleteAgent(String id);

  List<AgentDto> getAllAgents();

  void addCustomerPolicy(String agentId, String customerPolicyId);
}
