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
  public CustomerResponseModel getCustomer(@PathVariable String id) {

    return modelMapper.map(customerService.getUserByUserId(id), CustomerResponseModel.class);
  }

  @GetMapping
  public List<CustomerResponseModel> getAllCustomers(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "5") int limit) {

    List<CustomerResponseModel> returnValue = new ArrayList<>();
    List<CustomerDto> customers = customerService.getUsers(page, limit);

    for (CustomerDto customer : customers) {
      returnValue.add(modelMapper.map(customer, CustomerResponseModel.class));
    }

    return returnValue;
  }

  @PostMapping
  public CustomerResponseModel createCustomer(
      @RequestBody CustomerRequestModel customerRequestModel) {

    final CustomerDto customerDto = modelMapper.map(customerRequestModel, CustomerDto.class);

    return modelMapper.map(
        customerService.createCustomer(customerDto), CustomerResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public CustomerResponseModel updateCustomer(
      @PathVariable String id, @RequestBody CustomerRequestModel customerRequestModel) {

    final CustomerDto customerDto = modelMapper.map(customerRequestModel, CustomerDto.class);

    return modelMapper.map(
        customerService.updateCustomer(id, customerDto), CustomerResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public CustomerDto deleteCustomer(@PathVariable String id) {

    final CustomerDto returnValue = customerService.getUserByUserId(id);
    customerService.deleteCustomer(id);

    return returnValue;
  }
}
