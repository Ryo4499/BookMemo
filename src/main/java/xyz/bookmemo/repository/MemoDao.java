package xyz.bookmemo.repository;

import java.util.List;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Memo;

public interface MemoDao {
  /**
   * Find all distinct categories for the given account.
   *
   * @param account The account to search for.
   * @return A list of distinct categories for the given account.
   */
  List<String> findDistinctCategoryByAccount(Account account);

  /**
   * Count the number of Memo objects that have a particular Account object as their account property.
   *
   * @param account The account to search for.
   * @return Long
   */
  Long countMemoIdByAccount(Account account);

  /**
   * "Count the number of categories that contain the given category and are associated with the given
   * account."
   *
   * The first parameter is a String, the second is an Account object
   *
   * @param category The category to search for.
   * @param account The account to search for.
   * @return A Long
   */
  Long countCategoryByContainingCategoryAndAccount(
    String category,
    Account account
  );

  /**
   * Count the number of titles that contain the given title and belong to the given account.
   *
   * @param title The title of the post.
   * @param account The account that the title belongs to.
   * @return Long
   */
  Long countTitleByContainingTitleAndAccount(String title, Account account);

  /**
   * "Count the number of books with the given name that are owned by the given account."
   *
   * The first thing to notice is that the function name is very descriptive. It tells us exactly what
   * the function does
   *
   * @param bookName The book name to search for.
   * @param account The account that the book belongs to.
   * @return A Long
   */
  Long countBookNameByContainingBookNameAndAccount(
    String bookName,
    Account account
  );

  /**
   * Search for memos that are not associated with any condition.
   *
   * @param account The account that is currently logged in.
   * @param page The page number of the search results.
   * @param limit The number of memos to be returned.
   * @return A list of memos.
   */
  List<Memo> noConditionSearch(Account account, int page, int limit);

  /**
   * "Search for memos with a title that contains the given string, and return the results."
   *
   * The first parameter is the string to search for. The second parameter is the account to search in.
   * The third and fourth parameters are the page number and the number of results to return
   *
   * @param selectTitle The title you want to search for.
   * @param account The account that the memos are associated with.
   * @param page The page number of the search results.
   * @param limit The number of memos to be displayed per page.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> searchTitle(
    String selectTitle,
    Account account,
    int page,
    int limit
  );

  /**
   * "Search for memos in a category."
   *
   * The first parameter is the category to search for. The second parameter is the account to search
   * in. The third parameter is the page number to return. The fourth parameter is the number of
   * results to return per page
   *
   * @param selectCategory The category you want to search for.
   * @param account The account that the memos are associated with.
   * @param page The page number of the memo list to be retrieved.
   * @param limit The number of memos to be returned.
   * @return A list of memos that match the search criteria.
   */
  List<Memo> searchCategory(
    String selectCategory,
    Account account,
    int page,
    int limit
  );

  /**
   * "Search for memos with the given book name, and return the results."
   *
   * The first parameter is the book name to search for. The second parameter is the account that is
   * doing the search. The third and fourth parameters are the page number and the number of results to
   * return
   *
   * @param selectBookName The name of the book you want to search for.
   * @param account The account that is currently logged in.
   * @param page The page number of the search results.
   * @param limit The number of records to be displayed per page.
   * @return A list of Memo objects.
   */
  List<Memo> searchBookName(
    String selectBookName,
    Account account,
    int page,
    int limit
  );
}
