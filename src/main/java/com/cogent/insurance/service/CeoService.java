package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CeoDto;

import java.util.List;

public interface CeoService {

  CeoDto createCeo(CeoDto ceoDto);

  CeoDto getCeoById(String id);

  CeoDto updateCeo(String id, CeoDto ceoDto);

  void deleteCeo(String id);

  List<CeoDto> getAllCeo();
}
