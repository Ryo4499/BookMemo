package com.herokuapp.bookmemo.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.herokuapp.bookmemo.entity.Account;
import com.herokuapp.bookmemo.entity.Account_;
import com.herokuapp.bookmemo.entity.Memo;
import com.herokuapp.bookmemo.entity.Memo_;

@Repository
public class MemoDaoImpl implements MemoDao {

  private final EntityManager entityManager;

  @Autowired
  public MemoDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<String> findDistinctCategoryByAccount(Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<String> query = builder.createQuery(String.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(root.get(Memo_.category))
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()))
        .distinct(true);

    final TypedQuery<String> typedQuery = entityManager.createQuery(query);
    final List<String> categorys = typedQuery.getResultList();

    return categorys;
  }

  @Override
  public Long countMemoIdByAccount(Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(builder.count(root))
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()));

    final Long count = entityManager.createQuery(query).getSingleResult().longValue();

    return count;
  }

  @Override
  public Long countCategoryByContainingCategoryAndAccount(String category, Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(builder.count(root)).where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
        builder.like(builder.upper(root.get(Memo_.category)), category.toUpperCase()));

    final Long count = entityManager.createQuery(query).getSingleResult().longValue();

    return count;
  }

  @Override
  public Long countTitleByContainingTitleAndAccount(String title, Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(builder.count(root)).where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
        builder.like(builder.upper(root.get(Memo_.title)), "%" + title.toUpperCase() + "%"));

    final Long count = entityManager.createQuery(query).getSingleResult().longValue();

    return count;
  }

  @Override
  public Long countBookNameByContainingBookNameAndAccount(String bookName, Account account) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Long> query = builder.createQuery(Long.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(builder.count(root)).where(
        builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
        builder.like(builder.upper(root.get(Memo_.bookName)), "%" + bookName.toUpperCase() + "%"));

    final Long count = entityManager.createQuery(query).getSingleResult().longValue();

    return count;
  }

  @Override
  public List<Memo> noConditionSearch(Account account, int page, int limit) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(root)
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()))
        .orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();

    return memos;
  }

  @Override
  public List<Memo> searchTitle(String selectTitle, Account account, int page, int limit) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(root)
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
            builder.like(builder.upper(root.get(Memo_.title)),
                "%" + selectTitle.toUpperCase() + "%"))
        .orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));
    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
    return memos;
  }

  @Override
  public List<Memo> searchCategory(String selectCategory, Account account, int page, int limit) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(root)
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
            builder.equal(builder.upper(root.get(Memo_.category)), selectCategory.toUpperCase()))
        .orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
    return memos;
  }

  @Override
  public List<Memo> searchBookName(String selectBookName, Account account, int page, int limit) {
    final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    final CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
    final Root<Memo> root = query.from(Memo.class);
    query.select(root)
        .where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
            builder.like(builder.upper(root.get(Memo_.bookName)),
                "%" + selectBookName.toUpperCase() + "%"))
        .orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

    final TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
    final List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
    return memos;
  }
}
