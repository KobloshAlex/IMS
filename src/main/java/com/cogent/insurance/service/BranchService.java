package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.BranchDto;

import java.util.List;

public interface BranchService {

  BranchDto createBranch(BranchDto branchDto);

  BranchDto getBranchById(String id);

  BranchDto updateBranch(String id, BranchDto branchDto);

  void deleteBranch(String id);

  List<BranchDto> getAllBranches();
}
