package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

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

	void delete(long memoId);

}
