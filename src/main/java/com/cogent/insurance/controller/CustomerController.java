package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.GeneralRequestModel;
import com.cogent.insurance.model.response.GeneralResponseModel;
import com.cogent.insurance.service.CustomerService;
import com.cogent.insurance.shared.dto.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

  @GetMapping(path = ID_PATH)
  public GeneralResponseModel getCustomer(@PathVariable String id) {

    return modelMapper.map(customerService.getUserByUserId(id), GeneralResponseModel.class);
  }

  @PostMapping
  public GeneralResponseModel createCustomer(@RequestBody GeneralRequestModel generalRequestModel) {

    final CustomerDto customerDto = modelMapper.map(generalRequestModel, CustomerDto.class);

    return modelMapper.map(customerService.createCustomer(customerDto), GeneralResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public GeneralResponseModel updateCustomer(
      @PathVariable String id, @RequestBody GeneralRequestModel generalRequestModel) {

    final CustomerDto customerDto = modelMapper.map(generalRequestModel, CustomerDto.class);

    return modelMapper.map(
        customerService.updateCustomer(id, customerDto), GeneralResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public CustomerDto deleteCustomer(@PathVariable String id) {

    final CustomerDto returnValue = customerService.getUserByUserId(id);
    customerService.deleteCustomer(id);

    return returnValue;
  }

  @GetMapping
  public List<GeneralResponseModel> getAllCustomers(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "5") int limit) {

    List<GeneralResponseModel> returnValue = new ArrayList<>();
    List<CustomerDto> customers = customerService.getUsers(page, limit);

    for (CustomerDto customer : customers) {
      returnValue.add(modelMapper.map(customer, GeneralResponseModel.class));
    }

    return returnValue;
  }

  @PutMapping(path = "{customerId}/add-customer-policy/{customerPolicyId}")
  public void addCustomerPolicyToCustomer(
      @PathVariable String customerId, @PathVariable String customerPolicyId) {
    customerService.addCustomerPolicy(customerId, customerPolicyId);
  }
}
