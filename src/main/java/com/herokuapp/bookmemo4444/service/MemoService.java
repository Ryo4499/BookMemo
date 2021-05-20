package com.herokuapp.bookmemo4444.service;

import java.util.List;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoService {
	void save(Memo memo);
	List<Memo> getAll();
}
