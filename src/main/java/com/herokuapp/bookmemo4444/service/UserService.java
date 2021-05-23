package com.herokuapp.bookmemo4444.service;

import java.util.List;

import com.herokuapp.bookmemo4444.entity.User;

public interface UserService {
	boolean insert(User user);

	void update(User user);

	void delete(int userId);

	List<User> getAll();

	User findById(int userId);

	User findBySessionId(String sessionId);

	User findByEmailAndPass(String email, String password);
}
