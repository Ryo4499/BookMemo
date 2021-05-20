package com.herokuapp.bookmemo4444.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertUser(User user) {
		jdbcTemplate.update("INSERT INTO users(user_name,user_email,user_password,remember_user) VALUES (?,?,?,?)",
				user.getUserName(), user.getUserEmail(), user.getUserPassword(), user.getRememberUser());
	}

	@Override
	public List<User> getAll() {
		String sql = "SELECT * FROM users";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<User> list = new ArrayList<>();
		resultList.forEach(map -> {
			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			list.add(user);
		});
		return list;
	}

	@Override
	public int updateUser(User user) {
		return jdbcTemplate.update("UPDATE users SET user_name=?,email=?,password=?", user.getUserName(),
				user.getUserEmail(), user.getUserPassword());
	}

	@Override
	public int deleteUser(User user) {
		return jdbcTemplate.update("DELETE users FROM user_id=?",user.getUserId());
	}

	@Override
	public User getUser(int userId) {
		Map<String, Object> map =jdbcTemplate.queryForMap("SELECT * FROM users WHERE user_id = ?",userId);
		User user = new User();
		user.setUserId((int) map.get("user_id"));
		user.setUserName((String) map.get("user_name"));
		user.setUserEmail((String) map.get("user_email"));
		user.setUserPassword((String) map.get("user_password"));
		user.setRememberUser((String) map.get("remember_user"));
		return user;
	}

}
