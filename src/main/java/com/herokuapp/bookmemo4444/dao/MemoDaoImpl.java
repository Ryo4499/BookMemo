package com.herokuapp.bookmemo4444.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public class MemoDaoImpl implements MemoDao{

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public MemoDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Override
	public void insertMemo(Memo memo) {
		jdbcTemplate.update("INSERT INTO memos(title,content,category,book_name,user_id) VALUES(?,?,?,?,?)",memo.getTitle(),memo.getContent(),memo.getCategory(),memo.getBookName(),memo.getUser_id());
	}

	@Override
	public void updateMemo() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteMemo() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public List<Memo> getAll() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
