package com.herokuapp.bookmemo4444.service;

import java.util.List;

import com.herokuapp.bookmemo4444.entity.User;

public interface UserService {
	void insert(User user);

	void update(User user);

	void delete(String id);

	List<User> getAll();

	User findById(int id);
	
	User findBySessionId(String id);
}
