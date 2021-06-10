package com.herokuapp.bookmemo4444.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

@Repository

public interface MemoRepository extends JpaRepository<Memo, Long> {
	@Transactional(readOnly = true)
	@Query("select m.createdDate from Memo m where m.memoId = ?1")
	Date findByMemoId(Long memoId);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional(readOnly = false)
	@Query(value = "Delete FROM memos where account_id = :id", nativeQuery = true)
	void deleteByAccount(@Param("id") Long accountId);

	@Secured("ROLE_ADMIN")
	@Transactional(readOnly = true)
	@Query(value = "select * from memos where account_id = ?1", nativeQuery = true)
	List<Memo> findByAccountId(Long accountId);

	@Secured("ROLE_ADMIN")
	@Modifying(clearAutomatically = true, flushAutomatically = false)
	@Transactional(readOnly = false)
	@Query(value = "DELETE FROM memos WHERE memo_id = :memoId",nativeQuery = true)
	void deleteByMemoId(@Param("memoId") Long memoId);

}
