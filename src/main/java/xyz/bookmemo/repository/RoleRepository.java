package xyz.bookmemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.bookmemo.entity.Role;

@Repository
@Transactional(readOnly = true)
public interface RoleRepository extends JpaRepository<Role, Long> {
  /**
   * Find a Role by its authority.
   *
   * @param authority The name of the role.
   * @return A Role object
   */
  Role findByAuthority(String authority);
}
