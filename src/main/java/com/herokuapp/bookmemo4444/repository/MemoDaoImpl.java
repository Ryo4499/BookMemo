package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Account_;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.entity.Memo_;

@Repository
public class MemoDaoImpl implements MemoDao {

	private final EntityManager entityManager;

	@Autowired
	public MemoDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<String> findDistinctCategoryByAccount(Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root.get(Memo_.category))
				.where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId())).distinct(true);

		TypedQuery<String> typedQuery = entityManager.createQuery(query);
		List<String> categorys = typedQuery.getResultList();

		return categorys;
	}

	@Override
	public Long countMemoIdByAccount(Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(builder.count(root))
				.where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()));

		final Long count = entityManager.createQuery(query).getSingleResult().longValue();

		return count;
	}

	@Override
	public Long countCategoryByContainingCategoryAndAccount(String category, Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(builder.count(root)).where(
				builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
				builder.like(root.get(Memo_.category), "%" + category + "%"));

		final Long count = entityManager.createQuery(query).getSingleResult().longValue();

		return count;
	}

	@Override
	public Long countTitleByContainingTitleAndAccount(String title, Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(builder.count(root)).where(
				builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
				builder.like(root.get(Memo_.title), "%" + title + "%"));

		final Long count = entityManager.createQuery(query).getSingleResult().longValue();

		return count;
	}

	@Override
	public Long countBookNameByContainingBookNameAndAccount(String bookName, Account account) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(builder.count(root)).where(
				builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
				builder.like(root.get(Memo_.bookName), "%" + bookName + "%"));

		final Long count = entityManager.createQuery(query).getSingleResult().longValue();

		return count;
	}

	@Override
	public List<Memo> noConditionSearch(Account account, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root).where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()))
				.orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

		TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
		List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();

		return memos;
	}

	@Override
	public List<Memo> searchTitle(String selectTitle, Account account, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root)
				.where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
						builder.like(root.get(Memo_.title), "%" + selectTitle + "%"))
				.orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));
		TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
		List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
		return memos;
	}

	@Override
	public List<Memo> searchCategory(String selectCategory, Account account, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root)
				.where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
						builder.equal(root.get(Memo_.category), selectCategory))
				.orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

		TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
		List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
		return memos;
	}

	@Override
	public List<Memo> searchBookName(String selectBookName, Account account, int page, int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root)
				.where(builder.equal(root.get(Memo_.account).get(Account_.id), account.getId()),
						builder.like(root.get(Memo_.bookName), "%" + selectBookName + "%"))
				.orderBy(builder.desc(root.get(Memo_.updatedDate)), builder.desc(root.get(Memo_.memoId)));

		TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
		List<Memo> memos = typedQuery.setFirstResult(page).setMaxResults(limit).getResultList();
		return memos;
	}

	@Override
	@Transactional
	public void deleteByMemoId(Long memoId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaDelete<Memo> query = builder.createCriteriaDelete(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.where(builder.equal(root.get(Memo_.memoId), memoId));

		Query query2 = entityManager.createQuery(query);
		query2.executeUpdate();
	}

}
