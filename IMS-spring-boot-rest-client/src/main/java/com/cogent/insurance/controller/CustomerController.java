package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.GeneralRequestModel;
import com.cogent.insurance.model.response.GeneralResponseModel;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/customers")
public class CustomerController {

  private static final String ID_PATH = "/{id}";

  private final CustomerService customerService;
  private final ModelMapper modelMapper;

  public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
    this.customerService = customerService;
    this.modelMapper = modelMapper;
  }

  @Secured({"ROLE_MANAGER", "ROLE_AGENT"})
  @GetMapping(path = ID_PATH)
  public GeneralResponseModel getCustomer(@PathVariable String id) {

    return modelMapper.map(customerService.getCustomerByUserId(id), GeneralResponseModel.class);
  }

  @Secured({"ROLE_MANAGER"})
  @PostMapping
  public GeneralResponseModel createCustomer(@RequestBody GeneralRequestModel generalRequestModel) {

    final CustomerDto customerDto = modelMapper.map(generalRequestModel, CustomerDto.class);

    return modelMapper.map(customerService.createCustomer(customerDto), GeneralResponseModel.class);
  }

  @Secured({"ROLE_MANAGER", "ROLE_AGENT"})
  @PutMapping(path = ID_PATH)
  public GeneralResponseModel updateCustomer(
      @PathVariable String id, @RequestBody GeneralRequestModel generalRequestModel) {

    final CustomerDto customerDto = modelMapper.map(generalRequestModel, CustomerDto.class);

    return modelMapper.map(
        customerService.updateCustomer(id, customerDto), GeneralResponseModel.class);
  }

  @Secured({"ROLE_MANAGER"})
  @DeleteMapping(path = ID_PATH)
  public CustomerDto deleteCustomer(@PathVariable String id) {

    final CustomerDto returnValue = customerService.getCustomerByUserId(id);
    customerService.deleteCustomer(id);

    return returnValue;
  }

  @Secured({"ROLE_MANAGER", "ROLE_AGENT"})
  @GetMapping
  public List<GeneralResponseModel> getAllCustomers(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "15") int limit) {

    List<GeneralResponseModel> returnValue = new ArrayList<>();
    List<CustomerDto> customers = customerService.getCustomers(page, limit);

    for (CustomerDto customer : customers) {
      returnValue.add(modelMapper.map(customer, GeneralResponseModel.class));
    }

    return returnValue;
  }

  @Secured({"ROLE_MANAGER", "ROLE_AGENT"})
  @PutMapping(path = "{customerId}/add-customer-policy/{customerPolicyId}")
  public void addCustomerPolicyToCustomer(
      @PathVariable String customerId, @PathVariable String customerPolicyId) {
    customerService.addCustomerPolicy(customerId, customerPolicyId);
  }
}
