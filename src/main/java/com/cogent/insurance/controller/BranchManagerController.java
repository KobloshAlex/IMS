package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.MARequestModel;
import com.cogent.insurance.model.response.MAResponseModel;
import com.cogent.insurance.service.BranchManagerService;
import com.cogent.insurance.shared.dto.BranchManagerDto;
import org.modelmapper.ModelMapper;
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

@RestController
@RequestMapping("api/branch-managers")
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
  public MAResponseModel getBranchManager(@PathVariable String id) {
    return modelMapper.map(branchManagerService.getBranchManagerById(id), MAResponseModel.class);
  }

  @PostMapping
  public MAResponseModel createBranchManager(@RequestBody MARequestModel MARequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(MARequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.createBranchManager(branchManagerDto), MAResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public MAResponseModel updateBranchManager(
      @PathVariable String id, @RequestBody MARequestModel MARequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(MARequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.updateBranchManager(id, branchManagerDto), MAResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public BranchManagerDto deleteBranchManager(@PathVariable String id) {

    final BranchManagerDto returnValue = branchManagerService.getBranchManagerById(id);
    branchManagerService.deleteBranchManager(id);

    return returnValue;
  }

  @GetMapping
  public List<MAResponseModel> getAllBranchManagers() {

    List<MAResponseModel> returnValue = new ArrayList<>();
    final List<BranchManagerDto> branchManagers = branchManagerService.getAllBranchManagers();

    for (BranchManagerDto manager : branchManagers) {
      returnValue.add(modelMapper.map(manager, MAResponseModel.class));
    }

    return returnValue;
  }
}
