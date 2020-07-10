package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.PolicyDto;

import java.util.List;

public interface PolicyService {

  PolicyDto createPolicy(PolicyDto policyDto);

  PolicyDto getPolicyById(String id);

  PolicyDto updatePolicy(String id, PolicyDto policyDto);

  void deletePolicy(String id);

  List<PolicyDto> getAllPolicies();

  void addCustomerPolicy(String policyId, String customerPolicyId);
}
