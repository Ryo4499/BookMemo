package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.MemoRepository;

@Service
public class MemoServiceImpl implements MemoService {

	private final MemoRepository memoRepository;

	@Autowired
	MemoServiceImpl(MemoRepository memoRepository) {
		this.memoRepository = memoRepository;
	}

	@Override
	public List<String> findDistinctCategoryByAccount(Account account) {
		return memoRepository.findDistinctCategoryByAccount(account);
	}

	@Override
	public int countMemoIdByAccount(Account account) {
		return memoRepository.countMemoIdByAccount(account);
	}

	@Override
	public int countCategoryByCategoryAndAccount(String category, Account account) {
		String parsent = "%" + category + "%";
		return memoRepository.countCategoryByContainingCategoryAndAccount(parsent, account);
	}

	@Override
	public int countTitleByTitleAndAccount(String title, Account account) {
		String parsent = "%" + title + "%";
		return memoRepository.countTitleByContainingTitleAndAccount(parsent, account);
	}

	@Override
	public int countBookNameByBookNameAndAccount(String bookName, Account account) {
		String parsent = "%" + bookName + "%";
		return memoRepository.countBookNameByContainingBookNameAndAccount(parsent, account);
	}

	@Override
	public Optional<Memo> findByMemoId(Long id) {
		return memoRepository.findById(id);
	}

	@Override
	public void delete(long memoId) {
		delete(memoId);
	}

}
