package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public List<Memo> searchTitle(String title, Long id, int page, int limit) {
		page = page * limit;
		String parsent = "%" + title + "%";
		return memoRepository.findByContainingTitleAndAccountOrderByUpdatedDate(parsent, id, page, limit);
	}

	@Override
	public List<Memo> searchCategory(String category, Long id, int page, int limit) {
		page = page * limit;
		String parsent = "%" + category + "%";
		return memoRepository.findByContainingCategoryAndAccountOrderByUpdatedDate(parsent, id, page, limit);
	}

	@Override
	public List<Memo> searchBookName(String bookName, Long id, int page, int limit) {
		page = page * limit;
		String parsent = "%" + bookName + "%";
		return memoRepository.findByContainingBookNameAndAccountOrderByUpdatedDate(parsent, id, page, limit);
	}

	@Override
	public List<Memo> noConditionSearch(Long id, int page, int limit) {
		page = page * limit;
		return memoRepository.limitOffsetList(id, page, limit);
	}

	@Override
	public List<Memo> findDistinctCategoryByAccount(Account account) {
		return memoRepository.findDistinctCategoryByAccount(account);
	}

	@Override
	public int countMemoIdByAccount(Long memoId, Long accountId) {
		return memoRepository.countMemoIdByAccount(memoId, accountId);
	}

	@Override
	public int countCategoryByCategoryAndAccount(String category, Long accountId) {
		String parsent = "%" + category + "%";
		return memoRepository.countCategoryByContainingCategoryAndAccount(parsent, accountId);
	}

	@Override
	public int countTitleByTitleAndAccount(String title, Long accountId) {
		String parsent = "%" + title + "%";
		return memoRepository.countTitleByContainingTitleAndAccount(parsent, accountId);
	}

	@Override
	public int countBookNameByBookNameAndAccount(String bookName, Long accountId) {
		String parsent = "%" + bookName + "%";
		return memoRepository.countBookNameByContainingBookNameAndAccount(parsent, accountId);
	}

}
