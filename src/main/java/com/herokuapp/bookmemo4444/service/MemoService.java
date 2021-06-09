package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

@Service
public interface MemoService {
	@Transactional(readOnly = true)
	List<String> findDistinctCategoryByAccount(Account account);

	@Transactional(readOnly = true)
	int countMemoIdByAccount(Account account);

	@Transactional(readOnly = true)
	int countCategoryByCategoryAndAccount(String category, Account account);

	@Transactional(readOnly = true)
	int countTitleByTitleAndAccount(String title, Account account);

	@Transactional(readOnly = true)
	int countBookNameByBookNameAndAccount(String bookName, Account account);

	@Transactional(readOnly = true)
	Optional<Memo> findByMemoId(Long id);

	@Transactional(readOnly = false)
	void delete(long memoId);

	@Transactional(readOnly = true)
	List<Memo> noConditionSearch(CustomSecurityAccount customSecurityAccount, HashMap<String, String> search);

	@Transactional(readOnly = true)
	List<Memo> searchTitle(String selectTitle, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search);

	@Transactional(readOnly = true)
	List<Memo> searchCategory(String selectCategory, CustomSecurityAccount customSecurityAccount,
			HashMap<String, String> search);

}
