package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.BranchManagerRequestModel;
import com.cogent.insurance.model.response.BranchManagerResponseModel;
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
  public BranchManagerResponseModel getBranchManager(@PathVariable String id) {
    return modelMapper.map(
        branchManagerService.getBranchManagerById(id), BranchManagerResponseModel.class);
  }

  @PostMapping
  public BranchManagerResponseModel createBranchManager(
      @RequestBody BranchManagerRequestModel branchManagerRequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(branchManagerRequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.createBranchManager(branchManagerDto),
        BranchManagerResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public BranchManagerResponseModel updateBranchManager(
      @PathVariable String id, @RequestBody BranchManagerRequestModel branchManagerRequestModel) {

    final BranchManagerDto branchManagerDto =
        modelMapper.map(branchManagerRequestModel, BranchManagerDto.class);

    return modelMapper.map(
        branchManagerService.updateBranchManager(id, branchManagerDto),
        BranchManagerResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public BranchManagerDto deleteBranchManager(@PathVariable String id) {

    final BranchManagerDto returnValue = branchManagerService.getBranchManagerById(id);
    branchManagerService.deleteBranchManager(id);

    return returnValue;
  }

  @GetMapping
  public List<BranchManagerResponseModel> getAllBranchManagers() {

    List<BranchManagerResponseModel> returnValue = new ArrayList<>();
    final List<BranchManagerDto> branchManagers = branchManagerService.getAllBranchManagers();

    for (BranchManagerDto manager : branchManagers) {
      returnValue.add(modelMapper.map(manager, BranchManagerResponseModel.class));
    }

    return returnValue;
  }
}
