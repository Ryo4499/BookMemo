package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Role;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {
	@Secured(value = "ADMIN")
	Role findByAuthority(String authority);
	
}
