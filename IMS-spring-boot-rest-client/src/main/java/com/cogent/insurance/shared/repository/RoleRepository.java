package com.cogent.insurance.shared.repository;

import com.cogent.insurance.entity.Role;
import com.cogent.insurance.shared.Roles;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

  Optional<Role> findByName(Roles name);
}
