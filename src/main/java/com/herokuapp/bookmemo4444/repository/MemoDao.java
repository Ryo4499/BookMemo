package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoDao {
	void insertMemo(Memo memo);

	int updateMemo(Memo memo);

	int deleteMemo(long id);

	List<Memo> getFirstSix();

	List<Memo> getNextSix(int page);

	List<Memo> getAll();

	Memo findById(long id);

	List<Memo> getAllCategory();

	List<Memo> searchByCategory(String category);
	
	List<Memo> searchByTitle(String title);
}
