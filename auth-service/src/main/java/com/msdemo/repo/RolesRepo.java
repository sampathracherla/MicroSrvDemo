package com.msdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msdemo.model.Role;

@Repository
public interface RolesRepo extends JpaRepository<Role, Integer> {

	Role findByName(String name);
}
