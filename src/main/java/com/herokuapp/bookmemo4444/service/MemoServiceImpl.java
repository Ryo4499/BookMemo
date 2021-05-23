package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.MemoDao;

@Service
public class MemoServiceImpl implements MemoService {

	private MemoDao memoDao;

	@Autowired
	public MemoServiceImpl(MemoDao memoDao) {
		this.memoDao = memoDao;
	}

	@Override
	public void insert(Memo memo) {
		memoDao.insertMemo(memo);
	}

	@Override
	public List<Memo> getAll() {
		return memoDao.getAll();
	}

	@Override
	public void update(Memo memo) {
		memoDao.updateMemo(memo);
	}

	@Override
	public void delete(long id) {
		memoDao.deleteMemo(id);
	}

	@Override
	public Memo findById(long id) {
		return memoDao.findById(id);
	}

	@Override
	public List<Memo> getAllCategory() {
		return memoDao.getAllCategory();
	}

	@Override
	public int getMemoCount() {
		return memoDao.getMemoCount();
	}

	@Override
	public int getCategoryCount(String category) {
		return memoDao.getCategoryCount(category);
	}

	@Override
	public int getTitleCount(String title) {
		return memoDao.getTitleCount(title);
	}

	@Override
	public List<Memo> searchByCategory(HashMap<String, String> search, String category) {
		return memoDao.searchByCategory(search, category);
	}

	@Override
	public List<Memo> searchByTitle(HashMap<String, String> search, String title) {
		return memoDao.searchByTitle(search, title);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Memo> getMemoList(HashMap<String, String> search) {
		return memoDao.getMemoList(search);
	}

}
