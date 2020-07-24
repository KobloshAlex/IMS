package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.MARequestModel;
import com.cogent.insurance.model.response.ManagerResponseModel;
import com.cogent.insurance.service.BranchManagerService;
import com.cogent.insurance.shared.dto.BranchManagerDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/branch-managers")
@Secured("ROLE_CEO")
public class BranchManagerController {

  private static final String ID_PATH = "/{id}";

  private final BranchManagerService branchManagerService;
  private final ModelMapper modelMapper;

  public BranchManagerController(
      BranchManagerService branchManagerService, ModelMapper modelMapper) {
    this.branchManagerService = branchManagerService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(path = ID_PATH)
  public ManagerResponseModel getBranchManager(@PathVariable String id) {
    return modelMapper.map(
        branchManagerService.getBranchManagerById(id), ManagerResponseModel.class);
  }

  @PostMapping
  public ManagerResponseModel createBranchManager(@RequestBody MARequestModel MARequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(MARequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.createBranchManager(branchManagerDto), ManagerResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public ManagerResponseModel updateBranchManager(
      @PathVariable String id, @RequestBody MARequestModel MARequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(MARequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.updateBranchManager(id, branchManagerDto), ManagerResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public BranchManagerDto deleteBranchManager(@PathVariable String id) {

    final BranchManagerDto returnValue = branchManagerService.getBranchManagerById(id);
    branchManagerService.deleteBranchManager(id);

    return returnValue;
  }

  @GetMapping
  public List<ManagerResponseModel> getAllBranchManagers() {

    List<ManagerResponseModel> returnValue = new ArrayList<>();
    final List<BranchManagerDto> branchManagers = branchManagerService.getAllBranchManagers();

    for (BranchManagerDto manager : branchManagers) {
      returnValue.add(modelMapper.map(manager, ManagerResponseModel.class));
    }

    return returnValue;
  }

  @PutMapping(path = "{managerId}/add-agent/{agentId}")
  public void addAgentToManager(@PathVariable String managerId, @PathVariable String agentId) {
    branchManagerService.addAgent(managerId, agentId);
  }

  @PutMapping(path = "{managerId}/add-customer-policy/{customerPolicyId}")
  public void addCustomerPolicyToManager(
      @PathVariable String managerId, @PathVariable String customerPolicyId) {
    branchManagerService.addCustomerPolicy(managerId, customerPolicyId);
  }
}
