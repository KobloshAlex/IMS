package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.BranchManagerDto;

import java.util.List;

public interface BranchManagerService {

  BranchManagerDto createBranchManager(BranchManagerDto branchManagerDto);

  BranchManagerDto getBranchManagerById(String id);

  BranchManagerDto updateBranchManager(String id, BranchManagerDto branchManagerDto);

  void deleteBranchManager(String id);

  List<BranchManagerDto> getAllBranchManagers();
}
