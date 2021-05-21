package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoDao {
	void insertMemo(Memo memo);
	int updateMemo(Memo memo);
	int deleteMemo(long id);
	List<Memo> getAll();
	List<Memo> findByTitle(String title);
	List<Memo> findByCategory(String category);
	Memo findById(long id);
	List<String> getAllCategory();
}
