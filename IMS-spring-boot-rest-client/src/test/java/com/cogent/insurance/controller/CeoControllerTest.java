package com.cogent.insurance.controller;

import com.cogent.insurance.model.response.CeoResponseModel;
import com.cogent.insurance.service.impl.CeoServiceImpl;
import com.cogent.insurance.shared.dto.CeoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CeoControllerTest {

  private static final String ID = "kjsdkfjdk123";
  @InjectMocks CeoController ceoController;
  @Mock CeoServiceImpl ceoService;

  CeoDto ceoDto;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    ceoDto = new CeoDto();
    ceoDto.setCeoId(ID);
    ceoDto.setFirstName("Test");
    ceoDto.setLastName("Test");
    ceoDto.setAge(123);
    ceoDto.setEmail("test");
    ceoDto.setEncryptedPassword("test");
    ceoDto.setPassword("test");
    ceoDto.setSex('M');
    ceoDto.setId(1L);
  }

  @Test
  void getCustomer() {
    when(ceoService.getCeoById(anyString())).thenReturn(ceoDto);
    final CeoResponseModel ceo = ceoController.getCeo(ID);
    assertNotNull(ceo);
  }
}
