// package com.cogent.insurance;
//
// import com.cogent.insurance.entity.Role;
// import com.cogent.insurance.entity.User;
// import com.cogent.insurance.shared.Roles;
// import com.cogent.insurance.shared.Utils;
// import com.cogent.insurance.shared.repository.RoleRepository;
// import com.cogent.insurance.shared.repository.UserRepository;
// import org.springframework.boot.context.event.ApplicationReadyEvent;
// import org.springframework.context.event.EventListener;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;
//
// import java.util.Arrays;
// import java.util.HashSet;
// import java.util.Optional;
//
// @Component
// public class InitialAuthorityAndRoleSetup {
//
//  private final RoleRepository roleRepository;
//
//  private final Utils utils;
//  private final BCryptPasswordEncoder bCryptPasswordEncoder;
//  private final UserRepository userRepository;
//
//  public InitialAuthorityAndRoleSetup(
//      RoleRepository roleRepository,
//      Utils utils,
//      BCryptPasswordEncoder bCryptPasswordEncoder,
//      UserRepository userRepository) {
//    this.roleRepository = roleRepository;
//    this.utils = utils;
//    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//    this.userRepository = userRepository;
//  }
//
//  @EventListener
//  @Transactional
//  public void onApplicationEvent(ApplicationReadyEvent event) {
//    System.out.println("from ROLES ready event");
//
//    final Optional<Role> roleCustomer = createRole(Roles.ROLE_CUSTOMER);
//    final Optional<Role> roleCeo = createRole(Roles.ROLE_CEO);
//    final Optional<Role> roleAgent = createRole(Roles.ROLE_MANAGER);
//    final Optional<Role> roleManager = createRole(Roles.ROLE_AGENT);
//
//
//    User adminUser = new User();
//    adminUser.setUserId(utils.generateId(20));
//    adminUser.setEmail("admin");
//    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
//    adminUser.setRoles(new HashSet<>(Arrays.asList(roleCustomer, roleAgent, roleManager,
// roleCeo)));
//
//    if (userRepository.findByEmail("admin") == null) {
//      userRepository.save(adminUser);
//    }
//  }
//
//  @Transactional
//  public Optional<Role> createRole(Roles name) {
//
//    return roleRepository.findByName(name);
//  }
// }
