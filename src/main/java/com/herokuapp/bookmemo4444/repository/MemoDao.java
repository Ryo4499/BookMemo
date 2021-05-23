package com.herokuapp.bookmemo4444.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoDao {
	void insertMemo(Memo memo);

	int updateMemo(Memo memo);

	int deleteMemo(long id);

	List<Memo> getAll();

	Memo findById(long id);

	List<Memo> getAllCategory();

	List<Memo> searchByCategory(HashMap<String, String> search, String category);

	List<Memo> searchByTitle(HashMap<String, String> search, String title);

	int getMemoCount();
	
	int getCategoryCount();
	
	int getTitleCount();

	List<Memo> getMemoList(HashMap<String, String> search);
}
