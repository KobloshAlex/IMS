package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.MARequestModel;
import com.cogent.insurance.model.response.AgentResponseModel;
import com.cogent.insurance.service.AgentService;
import com.cogent.insurance.shared.dto.AgentDto;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/agents")
public class AgentController {

  private static final String ID_PATH = "/{id}";

  private final AgentService agentService;
  private final ModelMapper modelMapper;

  public AgentController(AgentService agentService, ModelMapper modelMapper) {
    this.agentService = agentService;
    this.modelMapper = modelMapper;
  }
  @Secured({"ROLE_AGENT", "ROLE_MANAGER"})
  @GetMapping(path = ID_PATH)
  public AgentResponseModel getAgent(@PathVariable String id) {
    return modelMapper.map(agentService.getAgentById(id), AgentResponseModel.class);
  }
  @Secured({"ROLE_MANAGER"})
  @PostMapping
  public AgentResponseModel createAgent(@RequestBody MARequestModel MARequestModel) {

    final AgentDto agentDto = modelMapper.map(MARequestModel, AgentDto.class);

    return modelMapper.map(agentService.createAgent(agentDto), AgentResponseModel.class);
  }
  @Secured({"ROLE_MANAGER"})
  @PutMapping(path = ID_PATH)
  public AgentResponseModel updateAgent(
      @PathVariable String id, @RequestBody MARequestModel MARequestModel) {

    final AgentDto agentDto = modelMapper.map(MARequestModel, AgentDto.class);

    return modelMapper.map(agentService.updateAgent(id, agentDto), AgentResponseModel.class);
  }
  @Secured({"ROLE_MANAGER"})
  @DeleteMapping(path = ID_PATH)
  public AgentDto deleteAgent(@PathVariable String id) {

    final AgentDto returnValue = agentService.getAgentById(id);
    agentService.deleteAgent(id);

    return returnValue;
  }
  @Secured({"ROLE_AGENT", "ROLE_MANAGER"})
  @GetMapping
  public List<AgentResponseModel> getAllAgents() {

    List<AgentResponseModel> returnValue = new ArrayList<>();
    final List<AgentDto> agents = agentService.getAllAgents();

    for (AgentDto agent : agents) {
      returnValue.add(modelMapper.map(agent, AgentResponseModel.class));
    }

    return returnValue;
  }
  @Secured({"ROLE_MANAGER"})
  @PutMapping(path = "{agentId}/add-customer-policy/{customerPolicyId}")
  public void addCustomerPolicyToAgent(
      @PathVariable String agentId, @PathVariable String customerPolicyId) {
    agentService.addCustomerPolicy(agentId, customerPolicyId);
  }
}
