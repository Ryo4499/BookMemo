package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Role;

@Transactional
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByAuthority(String authority);
}
