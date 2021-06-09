package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.MemoDao;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

@Service
@Transactional(readOnly = true)
public class MemoServiceImpl implements MemoService {

	private final MemoDao memoDao;

	@Autowired
	MemoServiceImpl(MemoDao memoDao) {
		this.memoDao = memoDao;
	}

	@Override
	public List<String> findDistinctCategoryByAccount(Account account) {
		return memoDao.findDistinctCategoryByAccount(account);
	}

	@Override
	public Long countMemoIdByAccount(Account account) {
		return memoDao.countMemoIdByAccount(account);
	}

	@Override
	public Long countCategoryByCategoryAndAccount(String category, Account account) {
		String parsent = "%" + category + "%";
		return memoDao.countCategoryByContainingCategoryAndAccount(parsent, account);
	}

	@Override
	public Long countTitleByTitleAndAccount(String title, Account account) {
		String parsent = "%" + title + "%";
		return memoDao.countTitleByContainingTitleAndAccount(parsent, account);
	}

	@Override
	public Long countBookNameByBookNameAndAccount(String bookName, Account account) {
		String parsent = "%" + bookName + "%";
		return memoDao.countBookNameByContainingBookNameAndAccount(parsent, account);
	}

	@Override
	public List<Memo> noConditionSearch(CustomSecurityAccount customSecurityAccount, HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoDao.noConditionSearch(customSecurityAccount, limit * page, limit);
	}

	@Override
	public List<Memo> searchTitle(String selectTitle, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoDao.searchTitle(selectTitle, customSecurityAccount, limit * page, limit);
	}

	@Override
	public List<Memo> searchCategory(String selectCategory, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoDao.searchCategory(selectCategory, customSecurityAccount, limit * page, limit);
	}

}
