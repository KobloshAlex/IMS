package com.cogent.insurance.security;

import com.cogent.insurance.entity.RoleEntity;
import com.cogent.insurance.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class UserPrincipals implements UserDetails {

  public static final long serialVersionUID = -5778558028759677596L;

  private final UserEntity userEntity;

  public UserPrincipals(UserEntity userEntity) {
    this.userEntity = userEntity;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    Collection<GrantedAuthority> authorities = new HashSet<>();
    Collection<RoleEntity> roles = userEntity.getRoles();

    roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

    return authorities;
  }

  @Override
  public String getPassword() {
    return userEntity.getEncryptedPassword();
  }

  @Override
  public String getUsername() {
    return userEntity.getEmail();
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
    return true;
  }
}
