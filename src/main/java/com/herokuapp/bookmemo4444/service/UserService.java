package com.herokuapp.bookmemo4444.service;

import java.util.List;

import com.herokuapp.bookmemo4444.entity.User;

public interface UserService {
	void save(User user);
	List<User> getAll();
}
