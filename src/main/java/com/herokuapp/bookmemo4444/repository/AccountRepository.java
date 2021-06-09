package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByAccountEmail(String accountEmail);

	Account findByAccountName(String accountName);
	
	@Modifying
	@Query(value = "update accounts set account_name = :name,account_email = :email,account_password = :password where account_id = :id",nativeQuery = true)
	void updateAccount(@Param("id")Long id,@Param("name")String name,@Param("email")String email,@Param("password")String password);
}
