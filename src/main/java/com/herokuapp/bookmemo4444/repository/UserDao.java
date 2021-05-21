package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.User;

@Repository
public interface UserDao {
	void insertUser(User user);

	int updateUser(User user);

	int deleteUser(int id);

	List<User> getAll();

	User findById(int id);
	
	User findBySessionId(String id);
}
