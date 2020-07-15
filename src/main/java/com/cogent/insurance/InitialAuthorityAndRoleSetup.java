package com.cogent.insurance;

import com.cogent.insurance.entity.RoleEntity;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.repository.AgentRepository;
import com.cogent.insurance.shared.repository.BranchManagerRepository;
import com.cogent.insurance.shared.repository.CeoRepository;
import com.cogent.insurance.shared.repository.CustomerRepository;
import com.cogent.insurance.shared.repository.RoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InitialAuthorityAndRoleSetup {

  private final RoleRepository roleRepository;
  private final CustomerRepository customerRepository;
  private final AgentRepository agentRepository;
  private final BranchManagerRepository branchManagerRepository;
  private final CeoRepository ceoRepository;
  private final Utils utils;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public InitialAuthorityAndRoleSetup(
      RoleRepository roleRepository,
      CustomerRepository customerRepository,
      AgentRepository agentRepository,
      BranchManagerRepository branchManagerRepository,
      CeoRepository ceoRepository,
      Utils utils,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.roleRepository = roleRepository;
    this.customerRepository = customerRepository;
    this.agentRepository = agentRepository;
    this.branchManagerRepository = branchManagerRepository;
    this.ceoRepository = ceoRepository;
    this.utils = utils;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    System.out.println("from ROLES ready event");

    final RoleEntity roleCustomer = createRole("ROLE_CUSTOMER");
    final RoleEntity roleCeo = createRole("ROLE_CEO");
    final RoleEntity roleAgent = createRole("ROLE_AGENT");
    final RoleEntity roleManager = createRole("ROLE_MANAGER");

    if (roleCeo == null || roleCustomer == null || roleAgent == null || roleManager == null) {
      return;
    }

    //    Set<RoleEntity> roles = new HashSet<>();
    //    roles.add(roleAgent);
    //    AgentEntity adminUser = new AgentEntity();
    //    adminUser.setAgentId(utils.generateId(20));
    //    adminUser.setFirstName("Admin");
    //    adminUser.setLastName("Admin");
    //    adminUser.setEmail("agent1@gmail.com");
    //    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
    //    adminUser.setBranchAddress("address");
    //    adminUser.setSex('M');
    //    adminUser.setAge(22);
    //    adminUser.setBranchCity("bos");
    //    adminUser.setBranchState("MA");
    //    adminUser.setRoles(roles);
    //
    //    agentRepository.save(adminUser);
    //
    //    System.out.println(adminUser.getRoles().toString());
  }

  @Transactional
  public RoleEntity createRole(String name) {

    RoleEntity role = roleRepository.findByName(name);
    if (role == null) {
      role = new RoleEntity(name);
      roleRepository.save(role);
    }

    return role;
  }
}
