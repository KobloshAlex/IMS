package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.CeoRequestModel;
import com.cogent.insurance.model.response.CeoResponseModel;
import com.cogent.insurance.service.CeoService;
import com.cogent.insurance.shared.dto.CeoDto;
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
@Secured("ROLE_CEO")
@RestController
@RequestMapping("api/ceo")
public class CeoController {

  private static final String ID_PATH = "/{id}";

  private final CeoService ceoService;
  private final ModelMapper modelMapper;

  public CeoController(CeoService ceoService, ModelMapper modelMapper) {
    this.ceoService = ceoService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(path = ID_PATH)
  public CeoResponseModel getCeo(@PathVariable String id) {

    return modelMapper.map(ceoService.getCeoById(id), CeoResponseModel.class);
  }

  @PostMapping
  public CeoResponseModel createCeo(@RequestBody CeoRequestModel ceoRequestModel) {

    final CeoDto ceoDto = modelMapper.map(ceoRequestModel, CeoDto.class);

    return modelMapper.map(ceoService.createCeo(ceoDto), CeoResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public CeoResponseModel updateCeo(
      @PathVariable String id, @RequestBody CeoRequestModel ceoRequestModel) {

    final CeoDto ceoDto = modelMapper.map(ceoRequestModel, CeoDto.class);

    return modelMapper.map(ceoService.updateCeo(id, ceoDto), CeoResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public CeoDto deleteCeo(@PathVariable String id) {

    final CeoDto returnValue = ceoService.getCeoById(id);
    ceoService.deleteCeo(id);

    return returnValue;
  }

  @GetMapping
  public List<CeoResponseModel> getAllCeo() {

    List<CeoResponseModel> returnValue = new ArrayList<>();
    List<CeoDto> ceo = ceoService.getAllCeo();

    for (CeoDto ceoDto : ceo) {
      returnValue.add(modelMapper.map(ceoDto, CeoResponseModel.class));
    }

    return returnValue;
  }

  @PutMapping(path = "{ceoId}/add-branch/{branchId}")
  public void addBranchToCeo(@PathVariable String ceoId, @PathVariable String branchId) {
    ceoService.addBranch(ceoId, branchId);
  }
}
