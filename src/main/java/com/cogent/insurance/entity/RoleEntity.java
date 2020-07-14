package com.cogent.insurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

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
  private Set<CustomerEntity> customers;

  @ManyToMany(mappedBy = "roles")
  private Set<CeoEntity> ceo;

  @ManyToMany(mappedBy = "roles")
  private Collection<AgentEntity> agents;

  @ManyToMany(mappedBy = "roles")
  private Set<BranchManagerEntity> managers;

  //  @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  //  @JoinTable(
  //      name = "roles_authorities",
  //      joinColumns = @JoinColumn(name = "roles_id", referencedColumnName = "id"),
  //      inverseJoinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"))
  //  private Collection<AuthorityEntity> authorities;

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

  public void setCustomers(Set<CustomerEntity> customers) {
    this.customers = customers;
  }

  public Set<CeoEntity> getCeo() {
    return ceo;
  }

  public void setCeo(Set<CeoEntity> ceo) {
    this.ceo = ceo;
  }

  public Collection<AgentEntity> getAgents() {
    return agents;
  }

  public void setAgents(Collection<AgentEntity> agents) {
    this.agents = agents;
  }

  public Set<BranchManagerEntity> getManagers() {
    return managers;
  }

  public void setManagers(Set<BranchManagerEntity> managers) {
    this.managers = managers;
  }

  @Override
  public String toString() {
    return "RoleEntity{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", customers="
        + customers
        + ", ceo="
        + ceo
        + ", agents="
        + agents
        + ", managers="
        + managers
        + '}';
  }
}
