package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.BranchManagerDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface BranchManagerService extends UserDetailsService {

  BranchManagerDto createBranchManager(BranchManagerDto branchManagerDto);

  BranchManagerDto getBranchManagerById(String id);

  BranchManagerDto updateBranchManager(String id, BranchManagerDto branchManagerDto);

  void deleteBranchManager(String id);

  List<BranchManagerDto> getAllBranchManagers();

  void addAgent(String managerId, String agentId);

  void addCustomerPolicy(String managerId, String policyId);
}
