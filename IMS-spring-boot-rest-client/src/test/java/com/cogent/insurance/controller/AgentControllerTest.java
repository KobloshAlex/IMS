package com.cogent.insurance.controller;

import com.cogent.insurance.model.response.AgentResponseModel;
import com.cogent.insurance.service.AgentService;
import com.cogent.insurance.shared.dto.AgentDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AgentControllerTest {

  private static final String ID = "kjsdkfjdk123";
  @InjectMocks AgentController agentController;
  @Mock AgentService agentService;

  AgentDto agentDto;

  @BeforeEach
  void setup() {
    MockitoAnnotations.initMocks(this);

    agentDto = new AgentDto();
    agentDto.setAgentId(ID);
    agentDto.setFirstName("Test");
    agentDto.setLastName("Test");
    agentDto.setAge(123);
    agentDto.setBranchAddress("test");
    agentDto.setBranchCity("test");
    agentDto.setBranchState("test");
    agentDto.setEmail("test");
    agentDto.setEncryptedPassword("test");
    agentDto.setPassword("test");
    agentDto.setSex('M');
  }

  @Test
  final void getAgents() {
    when(agentService.getAgentById(anyString())).thenReturn(agentDto);
    final AgentResponseModel agent = agentController.getAgent(ID);
    assertNotNull(agent);
  }
}
