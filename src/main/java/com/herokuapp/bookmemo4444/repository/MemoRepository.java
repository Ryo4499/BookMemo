package com.herokuapp.bookmemo4444.repository;

import java.util.List;
import java.util.Optional;

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
import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
@EnableJpaRepositories
public interface MemoRepository extends JpaRepository<Memo, Long> {

	@Query(value = "SELECT DISTINCT(m.category) FROM Memo m WHERE m.account IN :account")
	List<String> findDistinctCategoryByAccount(@Param("account") Account account);

	@Query(value = "SELECT COUNT(m) FROM Memo m WHERE m.account IN :account")
	int countMemoIdByAccount(@Param("account") Account account);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE category LIKE ?1,account_id = ?2", nativeQuery = true)
	int countCategoryByContainingCategoryAndAccount(String category, Account account);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE title LIKE ?1,account_id = ?2", nativeQuery = true)
	int countTitleByContainingTitleAndAccount(String title, Account account);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE book_name LIKE ?1,account_id = ?2", nativeQuery = true)
	int countBookNameByContainingBookNameAndAccount(String bookName, Account account);

}
