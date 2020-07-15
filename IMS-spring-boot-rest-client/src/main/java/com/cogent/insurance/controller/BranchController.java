package com.cogent.insurance.controller;

import com.cogent.insurance.service.BranchService;
import com.cogent.insurance.shared.dto.BranchDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Secured("ROLE_CEO")
@RestController
@RequestMapping("api/branch")
public class BranchController {

  private static final String ID_PATH = "/{id}";

  private final BranchService branchService;

  public BranchController(BranchService branchService) {
    this.branchService = branchService;
  }

  @GetMapping(path = ID_PATH)
  public BranchDto getBranch(@PathVariable String id) {

    return branchService.getBranchById(id);
  }

  @PostMapping
  public BranchDto createBranch(@RequestBody BranchDto branchDto) {

    return branchService.createBranch(branchDto);
  }

  @PutMapping(path = ID_PATH)
  public BranchDto updateBranch(@PathVariable String id, @RequestBody BranchDto branchDto) {

    return branchService.updateBranch(id, branchDto);
  }

  @DeleteMapping(path = ID_PATH)
  public BranchDto deleteBranch(@PathVariable String id) {

    final BranchDto returnValue = branchService.getBranchById(id);
    branchService.deleteBranch(id);

    return returnValue;
  }

  @GetMapping
  public List<BranchDto> getAllBranches() {

    return branchService.getAllBranches();
  }

  @PutMapping(path = "{branchId}/add-manager/{branchManagerId}")
  public void addManagerToBranch(
      @PathVariable String branchId, @PathVariable String branchManagerId) {
    branchService.addBranchManager(branchId, branchManagerId);
  }

  @PutMapping(path = "{branchId}/add-customer/{customerId}")
  public void addCustomerToBranch(@PathVariable String branchId, @PathVariable String customerId) {
    branchService.addCustomer(branchId, customerId);
  }
}
