package com.msdemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.msdemo.model.Privilege;

@Repository
public interface PrivilegeRepo extends JpaRepository<Privilege, Integer> {

	Privilege findByName(String name);
}
