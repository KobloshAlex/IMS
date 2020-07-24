package com.cogent.insurance.service.impl;

import com.cogent.insurance.entity.CeoEntity;
import com.cogent.insurance.shared.dto.CeoDto;
import com.cogent.insurance.shared.repository.CeoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CeoServiceImplTest {

  @Mock ModelMapper modelMapper;
  @Mock private CeoRepository ceoRepository;
  @Mock private CeoDto ceoDto = new CeoDto();
  @InjectMocks private CeoServiceImpl ceoService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.initMocks(this);

    ceoDto.setId(1);
    ceoDto.setCeoId("13asd");
    ceoDto.setAddress("13asd");
    ceoDto.setAge(12);
    ceoDto.setEmail("sda");
    ceoDto.setEncryptedPassword("sda");
    ceoDto.setPassword("sda");
    ceoDto.setFirstName("test");
    ceoDto.setLastName("sasdda");
    ceoDto.setSex('m');

    ceoDto.setBranches(new ArrayList<>());
    when(modelMapper.map(any(), any())).thenReturn(ceoDto);

  }

  @Test
  void getCeoById() {
    CeoEntity ceoEntity = new CeoEntity();
    ceoEntity.setId(1);
    ceoEntity.setCeoId("13asd");
    ceoEntity.setAddress("13asd");
    ceoEntity.setAge(12);
    ceoEntity.setEmail("sda");
    ceoEntity.setEncryptedPassword("sda");
    ceoEntity.setFirstName("test");
    ceoEntity.setLastName("sasdda");
    ceoEntity.setSex('m');
    ceoEntity.setBranches(new ArrayList<>());

    when(ceoRepository.findByCeoId(anyString())).thenReturn(ceoEntity);
    ceoDto = ceoService.getCeoById("13asd");

    assertNotNull(ceoDto);
    assertEquals("test", ceoDto.getFirstName());
  }
}
