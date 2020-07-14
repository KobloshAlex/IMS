package com.cogent.insurance.service;

import com.cogent.insurance.shared.dto.CeoDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CeoService extends UserDetailsService {

  CeoDto createCeo(CeoDto ceoDto);

  CeoDto getCeoById(String id);

  CeoDto updateCeo(String id, CeoDto ceoDto);

  void deleteCeo(String id);

  List<CeoDto> getAllCeo();

  void addBranch(String ceoId, String branchId);
}
