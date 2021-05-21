package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public void save(Memo memo) {
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
	public List<Memo> searchByTitle(String title) {
		return memoDao.findByTitle(title);
	}

	@Override
	public List<Memo> searchByCategory(String category) {
		return memoDao.findByCategory(category);
	}

	@Override
	public List<String> getAllCategory() {
		return memoDao.getAllCategory();
	}

}
