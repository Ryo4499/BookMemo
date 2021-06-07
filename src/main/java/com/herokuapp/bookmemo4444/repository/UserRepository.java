package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.bookmemo4444.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUserEmail(String userEmail);
}
