package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoService {
	void insert(Memo memo);

	void update(Memo memo);

	void delete(long id);

	List<Memo> getAll();

	Memo findById(long id);

	List<Memo> getAllCategory();

	int getMemoCount();
	
	int getCategoryCount();
	
	int getTitleCount();

	List<Memo> searchByCategory(HashMap<String, String> search, String category);

	List<Memo> searchByTitle(HashMap<String, String> search, String title);

	List<Memo> getMemoList(HashMap<String, String> search);
}
