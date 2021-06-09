package com.herokuapp.bookmemo4444.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Pattern;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Account_;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.entity.Memo_;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;

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
