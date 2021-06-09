package com.herokuapp.bookmemo4444.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

@Service
public interface MemoService {
	@Transactional(readOnly = true)
	List<Memo> searchTitle(String title, Long id, int page, int limit);

	@Transactional(readOnly = true)
	List<Memo> searchCategory(String category, Long id, int page, int limit);

	@Transactional(readOnly = true)
	List<Memo> searchBookName(String bookName, Long id, int page, int limit);

	@Transactional(readOnly = true)
	List<Memo> noConditionSearch(Long id, int page, int limit);

	@Transactional(readOnly = true)
	List<Memo> findDistinctCategoryByAccount(Account account);

	@Transactional(readOnly = true)
	int countMemoIdByAccount(Long memoId, Long accountId);

	@Transactional(readOnly = true)
	int countCategoryByCategoryAndAccount(String category, Long accountId);

	@Transactional(readOnly = true)
	int countTitleByTitleAndAccount(String title, Long accountId);

	@Transactional(readOnly = true)
	int countBookNameByBookNameAndAccount(String bookName, Long accountId);

}
