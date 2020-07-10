package com.cogent.insurance.controller;

import com.cogent.insurance.service.PolicyService;
import com.cogent.insurance.shared.dto.PolicyDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/policy")
public class PolicyController {

  private static final String ID_PATH = "/{id}";

  private final PolicyService policyService;

  public PolicyController(PolicyService policyService) {
    this.policyService = policyService;
  }

  @GetMapping(path = ID_PATH)
  public PolicyDto getPolicy(@PathVariable String id) {

    return policyService.getPolicyById(id);
  }

  @PostMapping
  public PolicyDto createPolicy(@RequestBody PolicyDto policyDto) {

    return policyService.createPolicy(policyDto);
  }

  @PutMapping(path = ID_PATH)
  public PolicyDto createPolicy(@PathVariable String id, @RequestBody PolicyDto policyDto) {

    return policyService.updatePolicy(id, policyDto);
  }

  @DeleteMapping(path = ID_PATH)
  public PolicyDto deletePolicy(@PathVariable String id) {

    final PolicyDto returnValue = policyService.getPolicyById(id);
    policyService.deletePolicy(id);

    return returnValue;
  }

  @GetMapping
  public List<PolicyDto> getAllBranches() {

    return policyService.getAllPolicies();
  }

  @PutMapping(path = "{policyId}/add-customer-policy/{customerPolicyId}")
  public void addCustomerPolicyToPolicy(
      @PathVariable String policyId, @PathVariable String customerPolicyId) {
    policyService.addCustomerPolicy(policyId, customerPolicyId);
  }
}
