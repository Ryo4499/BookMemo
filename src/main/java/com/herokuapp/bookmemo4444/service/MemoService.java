package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoService {
	void insert(Memo memo);

	void update(Memo memo);

	void delete(long id);

	List<Memo> getAll();

	List<Memo> getFirstSix();

	List<Memo> getNextSix(int page);

	Memo findById(long id);

	List<Memo> getAllCategory();

	List<Memo> searchByCategory(String category);

	List<Memo> searchByTitle(String title);

	int getMemoCount();

	List<Memo> getMemoList(HashMap<String, String> search);
}
