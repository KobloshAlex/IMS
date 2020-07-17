package com.cogent.insurance.controller;

import com.cogent.insurance.service.CustomerPolicyService;
import com.cogent.insurance.shared.dto.CustomerPolicyDto;
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

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Secured("ROLE_AGENT")
@RestController
@RequestMapping("api/customer-policies")
public class CustomerPolicyController {

  private static final String ID_PATH = "/{id}";

  private final CustomerPolicyService customerPolicyService;

  public CustomerPolicyController(CustomerPolicyService customerPolicyService) {
    this.customerPolicyService = customerPolicyService;
  }

  @GetMapping(path = ID_PATH)
  public CustomerPolicyDto getBranch(@PathVariable String id) {

    return customerPolicyService.getCustomerPolicy(id);
  }

  @PostMapping
  public CustomerPolicyDto createBranch(@RequestBody CustomerPolicyDto customerPolicyDto) {

    return customerPolicyService.createCustomerPolicy(customerPolicyDto);
  }

  @PutMapping(path = ID_PATH)
  public CustomerPolicyDto updateBranch(
      @PathVariable String id, @RequestBody CustomerPolicyDto customerPolicyDto) {

    return customerPolicyService.updateCustomerPolicy(id, customerPolicyDto);
  }

  @DeleteMapping(path = ID_PATH)
  public CustomerPolicyDto deleteBranch(@PathVariable String id) {

    final CustomerPolicyDto returnValue = customerPolicyService.getCustomerPolicy(id);
    customerPolicyService.deleteCustomerPolicy(id);

    return returnValue;
  }

  @GetMapping
  public List<CustomerPolicyDto> getAllBranches() {

    return customerPolicyService.getAllCustomerPolicies();
  }
}
