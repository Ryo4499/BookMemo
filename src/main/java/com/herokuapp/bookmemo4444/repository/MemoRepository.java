package com.herokuapp.bookmemo4444.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.relational.core.sql.TrueCondition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
@EnableJpaRepositories
public interface MemoRepository extends JpaRepository<Memo, Long> {

	@Query(value = "SELECT * FROM memos WHERE account_id = ?1 ORDER BY updated_date DESC,memo_id DESC OFFSET ?2 FETCH FIRST ?3 ROWS ONLY", nativeQuery = true)
	List<Memo> limitOffsetList(Long id, int page, int limit);

	@Query(value = "SELECT * FROM memos WHERE title LIKE ?1 AND account_id = ?2 ORDER BY updated_date DESC,memo_id DESC OFFSET ?3 FETCH FIRST ?4 ROWS ONLY", nativeQuery = true)
	List<Memo> findByContainingTitleAndAccountOrderByUpdatedDate(String title, Long id, int page, int limit);

	@Query(value = "SELECT * FROM memos WHERE category LIKE ?1 AND account_id = ?2 ORDER BY updated_date DESC,memo_id DESC OFFSET ?3 FETCH FIRST ?4 ROWS ONLY", nativeQuery = true)
	List<Memo> findByContainingCategoryAndAccountOrderByUpdatedDate(String category, Long id, int page, int limit);

	@Query(value = "SELECT * FROM memos WHERE book_name LIKE ?1 AND account_id = ?2 ORDER BY updated_date DESC,memo_id DESC OFFSET ?3 FETCH FIRST ?4 ROWS ONLY", nativeQuery = true)
	List<Memo> findByContainingBookNameAndAccountOrderByUpdatedDate(String bookName, Long id, int page, int limit);

	List<Memo> findDistinctCategoryByAccount(Account account);

	List<Memo> findByAccountOrderByUpdatedDate(Account account);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE memo_id = ?1,account_id = ?2", nativeQuery = true)
	int countMemoIdByAccount(Long memoId, Long accountId);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE category LIKE ?1,account_id = ?2", nativeQuery = true)
	int countCategoryByContainingCategoryAndAccount(String category, Long accountId);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE title LIKE ?1,account_id = ?2", nativeQuery = true)
	int countTitleByContainingTitleAndAccount(String title, Long accountId);

	@Query(value = "SELECT COUNT(memo_id) FROM memos WHERE book_name LIKE ?1,account_id = ?2", nativeQuery = true)
	int countBookNameByContainingBookNameAndAccount(String bookName, Long accountId);

}
