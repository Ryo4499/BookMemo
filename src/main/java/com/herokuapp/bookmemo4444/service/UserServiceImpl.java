package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.repository.UserDao;

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
			throw new UserNotFoundException("更新するユーザが存在しません");
		}
	}

	@Override
	public boolean insert(User user) {
		try {
			userDao.insertUser(user);
			return true;
		} catch (DuplicateKeyException e) {
			return false;
		}
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void delete(int userId) {
		if (userDao.deleteUser(userId) == 0) {
			throw new UserNotFoundException("削除するユーザが存在しません");
		}
	}

	@Override
	public User findById(int userId) {
		try {
			return userDao.findById(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new UserNotFoundException("ユーザが見つかりません");
		}
	}

	@Override
	public User findBySessionId(String id) {
		return userDao.findBySessionId(id);
	}

	@Override
	public User findByEmailAndPass(String email, String password) {
		User user;
		try {
			user = userDao.findByEmailAndPass(email, password);
		} catch (DataIntegrityViolationException e) {
			user = null;
		} catch (EmptyResultDataAccessException e) {
			user = null;
		}

		return user;
	}

}
