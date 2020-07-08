package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.CustomerRequestModel;
import com.cogent.insurance.model.response.CustomerResponseModel;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

  private final CustomerService customerService;
  private final ModelMapper modelMapper;

  public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
    this.customerService = customerService;
    this.modelMapper = modelMapper;
  }

  @GetMapping(path = "/{id}")
  public CustomerResponseModel getCustomer(@PathVariable String id) {

    return modelMapper.map(customerService.getUserByUserId(id), CustomerResponseModel.class);
  }

  @PostMapping
  public CustomerResponseModel createCustomer(
      @RequestBody CustomerRequestModel customerRequestModel) {

    final CustomerDto customerDto = modelMapper.map(customerRequestModel, CustomerDto.class);

    return modelMapper.map(
        customerService.createCustomer(customerDto), CustomerResponseModel.class);
  }

  @PutMapping(path = "/{id}")
  public CustomerResponseModel updateCustomer(
      @PathVariable String id, @RequestBody CustomerRequestModel customerRequestModel) {

    final CustomerDto customerDto = modelMapper.map(customerRequestModel, CustomerDto.class);

    return modelMapper.map(
            customerService.updateCustomer(id, customerDto), CustomerResponseModel.class);
  }

  @DeleteMapping
  public String deleteCustomer() {
    return "DELETE";
  }
}
