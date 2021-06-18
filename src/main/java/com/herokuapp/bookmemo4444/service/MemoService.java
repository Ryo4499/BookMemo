package com.herokuapp.bookmemo4444.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

@Service
@Transactional(readOnly = true)
public interface MemoService {

  List<String> findDistinctCategoryByAccount(Account account);

  Long countMemoIdByAccount(Account account);

  Long countCategoryByCategoryAndAccount(String category, Account account);

  Long countTitleByTitleAndAccount(String title, Account account);

  Long countBookNameByBookNameAndAccount(String bookName, Account account);

  List<Memo> noConditionSearch(CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

  List<Memo> searchTitle(String selectTitle, CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

  List<Memo> searchCategory(String selectCategory, CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

  List<Memo> searchBookName(String selectBook, CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

}
