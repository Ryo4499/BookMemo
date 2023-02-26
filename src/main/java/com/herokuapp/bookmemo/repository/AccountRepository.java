package com.herokuapp.bookmemo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.herokuapp.bookmemo.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
  @Transactional(readOnly = true)
  Account findByEmail(String email);

  @Transactional(readOnly = true)
  Account findByAccountName(String accountName);

  @Override
  @Secured("ROLE_ADMIN")
  @Transactional(readOnly = true)
  List<Account> findAll();

  @Modifying
  @Transactional(readOnly = false)
  @Query(
      value = "update accounts set account_name = :name,account_email = :email,account_password = :password where account_id = :id",
      nativeQuery = true)
  void updateAccount(@Param("id") Long id, @Param("name") String name, @Param("email") String email,
      @Param("password") String password);

}
