package com.cogent.insurance.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable {

  public static final long serialVersionUID = -8754833322824285177L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(nullable = false, length = 20)
  private String name;

  @ManyToMany(mappedBy = "roles")
  private Collection<CustomerEntity> customers;

  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  @JoinTable(
      name = "roles_authorities",
      joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
  private Collection<AuthorityEntity> authorities;

  public RoleEntity() {}

  public RoleEntity(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Collection<CustomerEntity> getCustomers() {
    return customers;
  }

  public void setCustomers(Collection<CustomerEntity> customers) {
    this.customers = customers;
  }

  public Collection<AuthorityEntity> getAuthorities() {
    return authorities;
  }

  public void setAuthorities(Collection<AuthorityEntity> authorities) {
    this.authorities = authorities;
  }
}
