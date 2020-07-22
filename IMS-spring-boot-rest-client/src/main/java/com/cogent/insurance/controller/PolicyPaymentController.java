package com.cogent.insurance.controller;

import com.cogent.insurance.entity.PolicyPaymentEntity;
import com.cogent.insurance.exception.ErrorMessages;
import com.cogent.insurance.exception.ServiceException;
import com.cogent.insurance.service.PolicyPayment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "http://localhost:4200")
public class PolicyPaymentController {

  final PolicyPayment articleService;

  public PolicyPaymentController(PolicyPayment articleService) {
    this.articleService = articleService;
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> detail(@PathVariable("id") long id) {
    if (!articleService.existsId(id))
      return new ResponseEntity<>(
          new ServiceException(ErrorMessages.NO_RECORD_FOUND_ID.getErrorMessage()),
          HttpStatus.NOT_FOUND);
    PolicyPaymentEntity policyPaymentEntity = articleService.getById(id).orElse(null);
    return new ResponseEntity<>(policyPaymentEntity, HttpStatus.OK);
  }
}
