package com.cogent.insurance;

import com.cogent.insurance.entity.RoleEntity;
import com.cogent.insurance.entity.UserEntity;
import com.cogent.insurance.shared.Roles;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.repository.RoleRepository;
import com.cogent.insurance.shared.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Component
public class InitialAuthorityAndRoleSetup {

  private final RoleRepository roleRepository;

  private final Utils utils;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final UserRepository userRepository;

  public InitialAuthorityAndRoleSetup(
      RoleRepository roleRepository,
      Utils utils,
      BCryptPasswordEncoder bCryptPasswordEncoder,
      UserRepository userRepository) {
    this.roleRepository = roleRepository;
    this.utils = utils;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    this.userRepository = userRepository;
  }

  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    System.out.println("from ROLES ready event");

    final RoleEntity roleCustomer = createRole(Roles.ROLE_CUSTOMER.name());
    final RoleEntity roleCeo = createRole(Roles.ROLE_CEO.name());
    final RoleEntity roleAgent = createRole(Roles.ROLE_MANAGER.name());
    final RoleEntity roleManager = createRole(Roles.ROLE_AGENT.name());

    if (roleCeo == null || roleCustomer == null || roleAgent == null || roleManager == null) {
      return;
    }

    UserEntity adminUser = new UserEntity();
    adminUser.setUserId(utils.generateId(20));
    adminUser.setFirstName("Admin");
    adminUser.setLastName("Admin");
    adminUser.setEmail("admin");
    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
    adminUser.setRoles(Arrays.asList(roleCustomer, roleAgent, roleManager, roleCeo));

    if (userRepository.findByEmail("admin") == null) {
      userRepository.save(adminUser);
    }
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
