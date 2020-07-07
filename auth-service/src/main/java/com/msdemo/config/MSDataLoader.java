package com.msdemo.config;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.msdemo.model.Privilege;
import com.msdemo.model.Role;
import com.msdemo.model.User;
import com.msdemo.repo.PrivilegeRepo;
import com.msdemo.repo.RolesRepo;
import com.msdemo.repo.UserRepo;

@Component
public class MSDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	boolean isSetup = false;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RolesRepo rolesRepo;
	
	@Autowired
	private PrivilegeRepo privilegeRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if(isSetup) {
			return;
		}
		Privilege readPrivilege = createPrivilegeIfNotFound("READ", "Can only read the existing data");
		Privilege createPrivilege = createPrivilegeIfNotFound("CREATE", "Can create data");
		Privilege editPrivilege = createPrivilegeIfNotFound("EDIT", "Can edit data");
		Privilege deletePrivilege = createPrivilegeIfNotFound("DELETE", "Can delete data");
		List<Privilege> accountAdminPrivilege = Arrays.asList(createPrivilege, editPrivilege, readPrivilege);
		List<Privilege> superAdminPrivilege = Arrays.asList(createPrivilege, editPrivilege, readPrivilege, deletePrivilege);
		List<Privilege> userPrivilege = Arrays.asList(readPrivilege);
		Role accountAdminRole = createRoleIfNotFound("ACCOUNT_ADMIN", "Can Create, Read and Edit an account", accountAdminPrivilege);
		Role superAdminRole = createRoleIfNotFound("SUPER_ADMIN", "Can Create, Read Edit and Delete an account", superAdminPrivilege);
		Role userRole = createRoleIfNotFound("USER", "Can only read an account", userPrivilege);
		User user = userRepo.findByEmail("racherla.sampath@gmail.com");
		if(user == null) {
			user = new User();
			user.setFirstName("Sampath");
			user.setLastName("Racherla");
			user.setEmail("racherla.sampath@gmail.com");
			user.setPasword(passwordEncoder.encode("P@ssword1"));
			user.setPhoneNumber("0000000000");
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			user.setCreated(timeStamp);
			user.setRoles(Arrays.asList(superAdminRole));
			userRepo.save(user);
		}
		isSetup = true;
	}
	
	@Transactional
	private Role createRoleIfNotFound(String name, String description, Collection<Privilege> privileges) {
		Role role = rolesRepo.findByName(name);
		if(role == null) {
			role = new Role();
			Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
			role.setName(name);
			role.setDescription(description);
			role.setPrivileges(privileges);
			role.setCreated(timeStamp);
			rolesRepo.save(role);
		}
		return role;
	}
	
	@Transactional
	private Privilege createPrivilegeIfNotFound(String name, String description) {
		Privilege privilege = privilegeRepo.findByName(name);
		if(privilege == null) {
			privilege = new Privilege();
			privilege.setName(name);
			privilege.setDescription(description);
			privilegeRepo.save(privilege);
		}
		return privilege;
	}

}
