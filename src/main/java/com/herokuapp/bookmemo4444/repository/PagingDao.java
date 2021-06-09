package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.h2.pagestore.db.PageDataOverflow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public class PagingDao {

	private final EntityManager entityManager;

	@Autowired
	public PagingDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	// String sql = "SELECT * FROM memos ORDER BY created_date DESC,memo_id DESC
	// OFFSET ? FETCH FIRST ? ROWS ONLY";
	List<Memo> noConditionSearch(@Param("account") Account account, @Param("page") int page,
			@Param("limit") int limit) {
		return null;
	}

	// String sql = "SELECT * FROM memos WHERE title = ? ORDER BY created_date
	// DESC,memo_id DESC OFFSET ? FETCH FIRST ? ROWS ONLY";
	List<Memo> searchTitle(String selectTitle, @Param("account") Account account, @Param("page") int page,
			@Param("limit") int limit) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Memo> query = builder.createQuery(Memo.class);
		Root<Memo> root = query.from(Memo.class);
		query.select(root).where(builder.equal(root.get(Memo_.account).get(Account_.id), query))

		TypedQuery<Memo> typedQuery = entityManager.createQuery(query);
		List<Memo> memos = typedQuery.setFirstResult(5).setMaxResults(5).getResultList();
		return memos;
	}

	// String sql = "SELECT * FROM memos WHERE category = ? ORDER BY created_date
	// DESC,memo_id DESC OFFSET ? FETCH FIRST ? ROWS ONLY";
	List<Memo> searchCategory(String selectCategory, @Param("account") Account account, @Param("page") int page,
			@Param("limit") int limit) {
		return null;
	}

}
