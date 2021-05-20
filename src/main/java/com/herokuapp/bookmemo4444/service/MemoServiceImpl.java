package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.herokuapp.bookmemo4444.dao.MemoDao;
import com.herokuapp.bookmemo4444.entity.Memo;

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

}
