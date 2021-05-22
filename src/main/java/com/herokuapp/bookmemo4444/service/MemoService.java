package com.herokuapp.bookmemo4444.service;

import java.util.List;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoService {
	void insert(Memo memo);

	void update(Memo memo);

	void delete(long id);

	List<Memo> getAll();

	List<Memo> getFirstSix();

	List<Memo> getNextSix(int page);

	List<Memo> searchByTitle(String title);

	List<Memo> searchByCategory(String category);

	Memo findById(long id);

	List<Memo> getAllCategory();
	
	List<Memo> getCategory(String category);
}
