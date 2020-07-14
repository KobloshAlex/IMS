package com.cogent.insurance;

import com.cogent.insurance.entity.AuthorityEntity;
import com.cogent.insurance.entity.RoleEntity;
import com.cogent.insurance.shared.Utils;
import com.cogent.insurance.shared.repository.AuthorityRepository;
import com.cogent.insurance.shared.repository.CustomerRepository;
import com.cogent.insurance.shared.repository.RoleRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@Component
public class InitialAuthorityAndRoleSetup {

  private final AuthorityRepository authorityRepository;
  private final RoleRepository roleRepository;
  private final CustomerRepository customerRepository;
  private final Utils utils;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public InitialAuthorityAndRoleSetup(
      AuthorityRepository authorityRepository,
      RoleRepository roleRepository,
      CustomerRepository customerRepository,
      Utils utils,
      BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.authorityRepository = authorityRepository;
    this.roleRepository = roleRepository;
    this.customerRepository = customerRepository;
    this.utils = utils;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @EventListener
  @Transactional
  public void onApplicationEvent(ApplicationReadyEvent event) {
    System.out.println("from authority ready event");

    final AuthorityEntity readAuthority = createAuthority("READ_AUTHORITY");
    final AuthorityEntity deleteAuthority = createAuthority("DELETE_AUTHORITY");
    final AuthorityEntity writeAuthority = createAuthority("WRITE_AUTHORITY");

    final RoleEntity roleCustomer = createRole("ROLE_CUSTOMER", singletonList(readAuthority));
    final RoleEntity roleAdmin =
        createRole("ROLE_ADMIN", asList(readAuthority, deleteAuthority, writeAuthority));

    if (roleAdmin == null) {
      return;
    }

    //    CustomerEntity adminUser = new CustomerEntity();
    //    adminUser.setCustomerId(utils.generateId(20));
    //    adminUser.setFirstName("Admin");
    //    adminUser.setLastName("Admin");
    //    adminUser.setEmail("Admin@gmail.com");
    //    adminUser.setEncryptedPassword(bCryptPasswordEncoder.encode("123"));
    //    adminUser.setAddress("address");
    //    adminUser.setSex('M');
    //    adminUser.setAge(22);
    //    adminUser.setRoles(singletonList(roleAdmin));
    //
    //    customerRepository.save(adminUser);
  }

  @Transactional
  public AuthorityEntity createAuthority(String name) {

    AuthorityEntity authority = authorityRepository.findByName(name);
    if (authority == null) {
      authority = new AuthorityEntity(name);
      authorityRepository.save(authority);
    }
    return authority;
  }

  @Transactional
  public RoleEntity createRole(String name, Collection<AuthorityEntity> authorities) {

    RoleEntity role = roleRepository.findByName(name);
    if (role == null) {
      role = new RoleEntity(name);
      role.setAuthorities(authorities);
      roleRepository.save(role);
    }

    return role;
  }
}
