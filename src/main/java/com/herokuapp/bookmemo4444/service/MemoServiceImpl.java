package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.stereotype.Service;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.MemoRepository;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

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

	@Override
	public List<Memo> noConditionSearch(CustomSecurityAccount customSecurityAccount, HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoRepository.noConditionSearch(customSecurityAccount, limit * page, limit);
	}

	@Override
	public List<Memo> searchTitle(String selectTitle, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoRepository.searchTitle(selectTitle, customSecurityAccount, limit * page, limit);
	}

	@Override
	public List<Memo> searchCategory(String selectCategory, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search) {
		int limit = Integer.parseInt(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		return memoRepository.searchCategory(selectCategory, customSecurityAccount, limit * page, limit);
	}

}
