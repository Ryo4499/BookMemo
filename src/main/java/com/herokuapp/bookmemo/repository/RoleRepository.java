package com.herokuapp.bookmemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.herokuapp.bookmemo.entity.Role;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {
  // これはAdmin権限にするな!!!!
  Role findByAuthority(String authority);
}
