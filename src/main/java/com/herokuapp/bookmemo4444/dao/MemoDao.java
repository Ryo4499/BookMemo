package com.herokuapp.bookmemo4444.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoDao {
	void insertMemo(Memo memo);
	void updateMemo(Memo memo);
	void deleteMemo(Memo memo);
	List<Memo> getAll();
}
