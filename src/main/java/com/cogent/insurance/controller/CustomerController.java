package com.cogent.insurance.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

  @GetMapping
  public String getCustomer() {
    return "GET";
  }

  @PostMapping
  public String createCustomer() {
    return "POST";
  }

  @PutMapping
  public String updateCustomer() {
    return "UPDATE";
  }

  @DeleteMapping
  public String deleteCustomer() {
    return "DELETE";
  }
}
