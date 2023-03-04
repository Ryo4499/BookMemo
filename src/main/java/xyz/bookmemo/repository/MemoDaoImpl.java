package xyz.bookmemo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Account_;
import xyz.bookmemo.entity.Memo;
import xyz.bookmemo.entity.Memo_;

/**
 * "This class is used to create queries that are
 * used to search for memos."
 *
 * The first thing to notice is that the class is annotated with `@Repository`. This is because the
 * class
 * is a repository
 */
@Repository
public class MemoDaoImpl implements MemoDao {

  private final EntityManager entityManager;

  @Autowired
  public MemoDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  /**
   * "Find all distinct categories for a given account."
   *
   * The first thing we do is create a CriteriaBuilder. This is the object that we use to build our
   * query
   *
   * @param account The account to search for.
   * @return A list of distinct categorys for a given account.
   */
  @Override
  public List<String> findDistinctCategoryByAccount(Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(root.get(Memo_.category))
      .where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId())
      )
      .distinct(true);

    final TypedQuery<String> typedQuery = entityManager.createQuery(query);
    final List<String> categorys = typedQuery.getResultList();

    return categorys;
  }

  /**
   * "Count the number of Memo objects that have a specific Account object."
   *
   * The first thing we do is create a CriteriaBuilder object. This is the object that we use to build
   * our query
   *
   * @param account The account to search for.
   * @return A count of the number of memos associated with the account.
   */
  @Override
  public Long countMemoIdByAccount(Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(builder.count(root))
      .where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId())
      );

    final Long count = entityManager
      .createQuery(query)
      .getSingleResult()
      .longValue();

    return count;
  }

  /**
   * "Count the number of Memo objects that have a category that contains the given category and are
   * associated with the given account."
   *
   * The first thing we do is create a CriteriaBuilder object. This is the object that we use to build
   * our query
   *
   * @param category The category to search for.
   * @param account The account to search for.
   * @return A Long object.
   */
  @Override
  public Long countCategoryByContainingCategoryAndAccount(
    String category,
    Account account
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(builder.count(root))
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.like(
          builder.upper(root.get(Memo_.category)),
          category.toUpperCase()
        )
      );

    final Long count = entityManager
      .createQuery(query)
      .getSingleResult()
      .longValue();

    return count;
  }

  /**
   * "Count the number of Memo entities that have a title that contains the given title and are
   * associated with the given Account."
   *
   * The first thing we do is create a CriteriaBuilder. This is the object that we use to build our
   * query
   *
   * @param title The title to search for.
   * @param account The account to search for memos.
   * @return A Long
   */
  @Override
  public Long countTitleByContainingTitleAndAccount(
    String title,
    Account account
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(builder.count(root))
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.like(
          builder.upper(root.get(Memo_.title)),
          "%" + title.toUpperCase() + "%"
        )
      );

    final Long count = entityManager
      .createQuery(query)
      .getSingleResult()
      .longValue();

    return count;
  }

  /**
   * "Count the number of Memo objects that have a bookName that contains the given bookName and are
   * associated with the given Account."
   *
   * The first thing to notice is that the function is annotated with the @Override annotation. This is
   * because the function is overriding a function in the MemoRepository interface
   *
   * @param bookName The book name to search for.
   * @param account The account to search for.
   * @return A Long
   */
  @Override
  public Long countBookNameByContainingBookNameAndAccount(
    String bookName,
    Account account
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(builder.count(root))
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.like(
          builder.upper(root.get(Memo_.bookName)),
          "%" + bookName.toUpperCase() + "%"
        )
      );

    final Long count = entityManager
      .createQuery(query)
      .getSingleResult()
      .longValue();

    return count;
  }

  /**
   * "Get the memos of the account, ordered by the updated date and memo id, and return the memos from
   * the page to the limit."
   *
   * The first thing to do is to create a CriteriaBuilder. This is used to create a CriteriaQuery
   *
   * @param account The account to search for.
   * @param page The page number to start from.
   * @param limit The number of results to return.
   * @return A list of memos.
   */
  @Override
  public List<Memo> noConditionSearch(Account account, int page, int limit) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(root)
      .where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId())
      )
      .orderBy(
        builder.desc(root.get(Memo_.updatedDate)),
        builder.desc(root.get(Memo_.memoId))
      );

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery
      .setFirstResult(page)
      .setMaxResults(limit)
      .getResultList();

    return memos;
  }

  /**
   * "Search for memos by title, and return the results in descending order of updated date and memo
   * ID."
   *
   * The first thing to note is that the function is annotated with `@Override`. This is because the
   * function is overriding a function in the interface
   *
   * @param selectTitle The title to search for.
   * @param account The account that the memo belongs to.
   * @param page The page number to start from.
   * @param limit The number of results to return.
   * @return A list of memos.
   */
  @Override
  public List<Memo> searchTitle(
    String selectTitle,
    Account account,
    int page,
    int limit
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(root)
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.like(
          builder.upper(root.get(Memo_.title)),
          "%" + selectTitle.toUpperCase() + "%"
        )
      )
      .orderBy(
        builder.desc(root.get(Memo_.updatedDate)),
        builder.desc(root.get(Memo_.memoId))
      );
    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery
      .setFirstResult(page)
      .setMaxResults(limit)
      .getResultList();
    return memos;
  }

  @Override
  public List<Memo> searchCategory(
    String selectCategory,
    Account account,
    int page,
    int limit
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(root)
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.equal(
          builder.upper(root.get(Memo_.category)),
          selectCategory.toUpperCase()
        )
      )
      .orderBy(
        builder.desc(root.get(Memo_.updatedDate)),
        builder.desc(root.get(Memo_.memoId))
      );

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery
      .setFirstResult(page)
      .setMaxResults(limit)
      .getResultList();
    return memos;
  }

  @Override
  public List<Memo> searchBookName(
    String selectBookName,
    Account account,
    int page,
    int limit
  ) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query
      .select(root)
      .where(
        builder.equal(
          root.get(Memo_.account).get(Account_.id),
          account.getId()
        ),
        builder.like(
          builder.upper(root.get(Memo_.bookName)),
          "%" + selectBookName.toUpperCase() + "%"
        )
      )
      .orderBy(
        builder.desc(root.get(Memo_.updatedDate)),
        builder.desc(root.get(Memo_.memoId))
      );

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery
      .setFirstResult(page)
      .setMaxResults(limit)
      .getResultList();
    return memos;
  }
}
