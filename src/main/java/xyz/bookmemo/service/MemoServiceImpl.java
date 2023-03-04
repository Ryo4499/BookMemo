package xyz.bookmemo.service;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Memo;
import xyz.bookmemo.repository.MemoDao;
import xyz.bookmemo.security.CustomSecurityAccount;

@Service
@Transactional(readOnly = true)
public class MemoServiceImpl implements MemoService {

  private final MemoDao memoDao;

  @Autowired
  MemoServiceImpl(MemoDao memoDao) {
    this.memoDao = memoDao;
  }

  /**
   * > Find all distinct categories of memos for a given account
   *
   * @param account The account to search for.
   * @return A list of distinct categories for the given account.
   */
  @Override
  public List<String> findDistinctCategoryByAccount(Account account) {
    return memoDao.findDistinctCategoryByAccount(account);
  }

  /**
   * > Counts the number of memos that are associated with a given account
   *
   * @param account The account to search for.
   * @return The number of memos associated with the account.
   */
  @Override
  public Long countMemoIdByAccount(Account account) {
    return memoDao.countMemoIdByAccount(account);
  }

  /**
   * > Counts the number of memos that have a category that contains the given category and are
   * associated with the given account
   *
   * @param category The category to search for.
   * @param account The account that the memos belong to.
   * @return A Long
   */
  @Override
  public Long countCategoryByCategoryAndAccount(
    String category,
    Account account
  ) {
    return memoDao.countCategoryByContainingCategoryAndAccount(
      category,
      account
    );
  }

  /**
   * "Count the number of memos with the given title and account."
   *
   * The first line of the function is the JavaDoc comment. It's a good idea to include a JavaDoc
   * comment for every function
   *
   * @param title The title of the memo.
   * @param account The account that the memo belongs to.
   * @return A Long object.
   */
  @Override
  public Long countTitleByTitleAndAccount(String title, Account account) {
    return memoDao.countTitleByContainingTitleAndAccount(title, account);
  }

  /**
   * > Count the number of Memo objects that have a bookName that contains the given bookName and that
   * belong to the given account
   *
   * @param bookName The name of the book to search for.
   * @param account The account that the memo belongs to.
   * @return A Long
   */
  @Override
  public Long countBookNameByBookNameAndAccount(
    String bookName,
    Account account
  ) {
    return memoDao.countBookNameByContainingBookNameAndAccount(
      bookName,
      account
    );
  }

  /**
   * > This function returns a list of memos that are owned by the user who is logged in
   *
   * @param customSecurityAccount The user who is logged in.
   * @param search A HashMap that contains the search parameters.
   * @return A list of memos.
   */
  @Override
  public List<Memo> noConditionSearch(
    CustomSecurityAccount customSecurityAccount,
    HashMap<String, String> search
  ) {
    final int limit = Integer.parseInt(search.get("limit"));
    final int page = Integer.valueOf(search.get("page")) - 1;
    return memoDao.noConditionSearch(
      customSecurityAccount,
      limit * page,
      limit
    );
  }

  /**
   * > This function is used to search for a memo by title
   *
   * @param selectTitle The title of the memo to be searched.
   * @param customSecurityAccount The user who is logged in.
   * @param search A HashMap that contains the limit and page values.
   * @return A list of memos that match the search criteria.
   */
  @Override
  public List<Memo> searchTitle(
    String selectTitle,
    CustomSecurityAccount customSecurityAccount,
    HashMap<String, String> search
  ) {
    final int limit = Integer.parseInt(search.get("limit"));
    final int page = Integer.valueOf(search.get("page")) - 1;
    return memoDao.searchTitle(
      selectTitle,
      customSecurityAccount,
      limit * page,
      limit
    );
  }

  /**
   * > This function is used to search for memos by category
   *
   * @param selectCategory The category to search for.
   * @param customSecurityAccount The user who is logged in.
   * @param search HashMap<String, String>
   * @return A list of memos.
   */
  @Override
  public List<Memo> searchCategory(
    String selectCategory,
    CustomSecurityAccount customSecurityAccount,
    HashMap<String, String> search
  ) {
    final int limit = Integer.parseInt(search.get("limit"));
    final int page = Integer.valueOf(search.get("page")) - 1;
    return memoDao.searchCategory(
      selectCategory,
      customSecurityAccount,
      limit * page,
      limit
    );
  }

  /**
   * It searches for a book name.
   *
   * @param selectBook The name of the book to search for.
   * @param customSecurityAccount The user's account information
   * @param search a HashMap that contains the search parameters.
   * @return A list of Memo objects.
   */
  @Override
  public List<Memo> searchBookName(
    String selectBook,
    CustomSecurityAccount customSecurityAccount,
    HashMap<String, String> search
  ) {
    final int limit = Integer.parseInt(search.get("limit"));
    final int page = Integer.valueOf(search.get("page")) - 1;
    return memoDao.searchBookName(
      selectBook,
      customSecurityAccount,
      page,
      limit
    );
  }
}
