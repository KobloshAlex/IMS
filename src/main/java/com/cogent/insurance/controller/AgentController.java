package com.cogent.insurance.controller;

import com.cogent.insurance.model.request.MARequestModel;
import com.cogent.insurance.model.response.MAResponseModel;
import com.cogent.insurance.service.AgentService;
import com.cogent.insurance.shared.dto.AgentDto;
import org.modelmapper.ModelMapper;
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

  @GetMapping(path = ID_PATH)
  public MAResponseModel getBranchManager(@PathVariable String id) {
    return modelMapper.map(agentService.getAgentById(id), MAResponseModel.class);
  }

  @PostMapping
  public MAResponseModel createBranchManager(@RequestBody MARequestModel MARequestModel) {

    final AgentDto agentDto = modelMapper.map(MARequestModel, AgentDto.class);

    return modelMapper.map(agentService.createAgent(agentDto), MAResponseModel.class);
  }

  @PutMapping(path = ID_PATH)
  public MAResponseModel updateBranchManager(
      @PathVariable String id, @RequestBody MARequestModel MARequestModel) {

    final AgentDto agentDto = modelMapper.map(MARequestModel, AgentDto.class);

    return modelMapper.map(agentService.updateAgent(id, agentDto), MAResponseModel.class);
  }

  @DeleteMapping(path = ID_PATH)
  public AgentDto deleteBranchManager(@PathVariable String id) {

    final AgentDto returnValue = agentService.getAgentById(id);
    agentService.deleteAgent(id);

    return returnValue;
  }

  @GetMapping
  public List<MAResponseModel> getAllBranchManagers() {

    List<MAResponseModel> returnValue = new ArrayList<>();
    final List<AgentDto> agents = agentService.getAllAgents();

    for (AgentDto agent : agents) {
      returnValue.add(modelMapper.map(agent, MAResponseModel.class));
    }

    return returnValue;
  }
}