package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
	public List<Memo> getFirstSix() {
		return memoDao.getFirstSix();
	}

	@Override
	public List<Memo> getNextSix(int page) {
		return memoDao.getNextSix(page);
	}

	@Override
	public List<Memo> searchByCategory(String category) {
		return memoDao.searchByCategory(category);
	}

	@Override
	public List<Memo> searchByTitle(String title) {
		return memoDao.searchByTitle(title);
	}

	@Override
	public int getMemoCount() {
		return memoDao.getMemoCount();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Memo> getMemoList(HashMap<String, String> search) {
		return memoDao.getMemoList(search);
	}

}
