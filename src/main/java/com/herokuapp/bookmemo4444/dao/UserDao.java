package com.herokuapp.bookmemo4444.dao;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.User;


@Repository
public interface UserDao {
	void insertUser(User user);
	List<User> getAll();
}
