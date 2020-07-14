package com.cogent.insurance.security.principal;

import com.cogent.insurance.entity.AuthorityEntity;
import com.cogent.insurance.entity.CustomerEntity;
import com.cogent.insurance.entity.RoleEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomerPrincipal implements UserDetails {

  public static final long serialVersionUID = -8500151367184255041L;

  private final CustomerEntity customerEntity;

  public CustomerPrincipal(CustomerEntity customerEntity) {
    this.customerEntity = customerEntity;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    List<GrantedAuthority> authorities = new ArrayList<>();
    List<AuthorityEntity> authorityEntities = new ArrayList<>();

    final Collection<RoleEntity> roles = customerEntity.getRoles();

    if (roles == null) {
      return authorities;
    }

    roles.forEach(
        role -> {
          authorities.add(new SimpleGrantedAuthority(role.getName()));
          authorityEntities.addAll(role.getAuthorities());
        });

    authorityEntities.forEach(
        authorityEntity -> authorities.add(new SimpleGrantedAuthority(authorityEntity.getName())));

    return authorities;
  }

  @Override
  public String getPassword() {
    return customerEntity.getEncryptedPassword();
  }

  @Override
  public String getUsername() {
    return customerEntity.getEmail();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO: 7/13/2020 Replace with email verification link
    return true;
  }
}
