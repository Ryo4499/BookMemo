package com.herokuapp.bookmemo4444.repository;

import java.util.List;
import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoDao {

  List<String> findDistinctCategoryByAccount(Account account);

  Long countMemoIdByAccount(Account account);

  Long countCategoryByContainingCategoryAndAccount(String category, Account account);

  Long countTitleByContainingTitleAndAccount(String title, Account account);

  Long countBookNameByContainingBookNameAndAccount(String bookName, Account account);

  List<Memo> noConditionSearch(Account account, int page, int limit);

  List<Memo> searchTitle(String selectTitle, Account account, int page, int limit);

  List<Memo> searchCategory(String selectCategory, Account account, int page, int limit);

  List<Memo> searchBookName(String selectBookName, Account account, int page, int limit);

}
