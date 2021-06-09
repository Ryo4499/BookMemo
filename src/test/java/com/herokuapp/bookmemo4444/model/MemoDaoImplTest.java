package com.herokuapp.bookmemo4444.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.herokuapp.bookmemo4444.entity.Memo;

class MemoDaoImplTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testLimitOffsetList() {
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		        int pageSize = 15;

		        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		        CriteriaQuery<Memo> criteriaQuery = criteriaBuilder.createQuery(Memo.class);
		        Root<Memo> root = criteriaQuery.from(Memo.class);

		        CriteriaQuery<Memo> selectQuery = criteriaQuery.select(root);
		        TypedQuery<Memo> typedQuery = session.createQuery(selectQuery);

		        // setting pagination limits
		        typedQuery.setFirstResult(10);
		        typedQuery.setMaxResults(pageSize);

		        printMemos(typedQuery.getResultList());

		    }catch(Exception e){
		        e.printStackTrace();
		    }
	}

	private void printMemos(List<Memo> resultList) {
		resultList.forEach(System.out::println);
	}

	@Test
	void testFindByContainingTitleAndAccountOrderByUpdatedDate() {
		fail("まだ実装されていません");
	}

	@Test
	void testFindByContainingCategoryAndAccountOrderByUpdatedDate() {
		fail("まだ実装されていません");
	}

	@Test
	void testFindByContainingBookNameAndAccountOrderByUpdatedDate() {
		fail("まだ実装されていません");
	}

}
