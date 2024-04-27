package xyz.bookmemo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Memo;
import xyz.bookmemo.security.CustomSecurityAccount;

@Service
@Transactional(readOnly = true)
public interface MemoService {
  /**
   * Find all distinct categories for the given account.
   *
   * @param account The account to search for.
   * @return A list of distinct categories for the given account.
   */
  List<String> findDistinctCategoryByAccount(Account account);

  /**
   * Count the number of Memo objects that have a particular Account object as their account
   * property.
   *
   * @param account The account to search for.
   * @return Long
   */
  Long countMemoIdByAccount(Account account);

  /**
   * Count the number of categories by category and account.
   *
   * @param category The category name
   * @param account The account to search for.
   * @return Long
   */
  Long countCategoryByCategoryAndAccount(String category, Account account);

  /**
   * Count the number of titles that have the given title and account.
   *
   * @param title The title of the post
   * @param account The account that the title belongs to.
   * @return A Long
   */
  Long countTitleByTitleAndAccount(String title, Account account);

  /**
   * Count the number of books with the given book name and account.
   *
   * @param bookName the name of the book
   * @param account The account that the book belongs to.
   * @return Long
   */
  Long countBookNameByBookNameAndAccount(String bookName, Account account);

  /**
   * "Search for memos with no conditions."
   *
   * <p>The first parameter is a custom security account. The second parameter is a hash map of
   * search conditions
   *
   * @param customSecurityAccount The account of the user who is currently logged in.
   * @param search A HashMap of search parameters. The key is the column name, and the value is the
   *     search term.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> noConditionSearch(
      CustomSecurityAccount customSecurityAccount, HashMap<String, String> search);

  /**
   * It searches for a memo with a title that matches the selectTitle parameter.
   *
   * @param selectTitle The title of the memo you want to search for.
   * @param customSecurityAccount The user who is logged in.
   * @param search A HashMap that contains the search parameters.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> searchTitle(
      String selectTitle,
      CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

  /**
   * It searches for a memo in a category.
   *
   * @param selectCategory The category you want to search.
   * @param customSecurityAccount The user who is logged in.
   * @param search A HashMap that contains the search parameters.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> searchCategory(
      String selectCategory,
      CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);

  /**
   * It searches for a memo in a category.
   *
   * @param selectCategory The category you want to search.
   * @param customSecurityAccount The user who is logged in.
   * @param search A HashMap that contains the search parameters.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> searchBookName(
      String selectBook,
      CustomSecurityAccount customSecurityAccount,
      HashMap<String, String> search);
}
