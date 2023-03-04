package xyz.bookmemo.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import xyz.bookmemo.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
  /**
   * > Find the createdDate of the Memo with the given memoId
   *
   * @param memoId The memo ID
   * @return The createdDate of the Memo with the memoId of the parameter.
   */
  @Transactional(readOnly = true)
  @Query("select m.createdDate from Memo m where m.memoId = ?1")
  Date findByMemoId(Long memoId);

  /**
   * "Find all memos that belong to the account with the given accountId."
   *
   * The @Secured annotation is a Spring Security annotation that restricts access
   * to this function to
   * users with the ROLE_ADMIN role
   *
   * @param accountId The ID of the account to search for.
   */
  @Modifying(clearAutomatically = true, flushAutomatically = true)
  @Transactional(readOnly = false)
  @Query(value = "Delete FROM memos where account_id = :id", nativeQuery = true)
  void deleteByAccount(@Param("id") Long accountId);

  /**
   * "Find all memos that belong to the account with the given accountId."
   *
   * The @Secured annotation is a Spring Security annotation that restricts access to this function to
   * users with the ROLE_ADMIN role
   *
   * @param accountId The ID of the account to search for.
   * @return A list of memos
   */
  @Secured("ROLE_ADMIN")
  @Transactional(readOnly = true)
  @Query(
    value = "select * from memos where account_id = ?1",
    nativeQuery = true
  )
  List<Memo> findByAccountId(Long accountId);

  /**
   * This function deletes a memo from the database by memoId
   *
   * @param memoId The ID of the memo to be deleted.
   */
  @Secured("ROLE_ADMIN")
  @Modifying(clearAutomatically = true, flushAutomatically = false)
  @Transactional(readOnly = false)
  @Query(
    value = "DELETE FROM memos WHERE memo_id = :memoId",
    nativeQuery = true
  )
  void deleteByMemoId(@Param("memoId") Long memoId);
}
