package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.bookmemo4444.dao.UserDao;
import com.herokuapp.bookmemo4444.entity.User;

@Service
public class UserServiceImpl implements UserService {
	private final UserDao userDao;

	@Autowired
	UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void update(User user) {
		if (userDao.updateUser(user) == 0) {
			throw new UserNotFoundException("Can't Found User");
		}
	}

	@Override
	public void save(User user) {
		userDao.insertUser(user);

	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

}
