package com.herokuapp.bookmemo4444.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoDao {
	void insertMemo(Memo memo);
	int updateMemo(Memo memo);
	int deleteMemo(Memo memo);
	List<Memo> getAll();
	Memo findById(long id);
	List<Memo> findByTitle(String title);
	List<Memo> findByCategory(String category);
	List<String> getAllCategory();
}
