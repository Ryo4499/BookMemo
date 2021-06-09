package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{
	@Transactional(readOnly = true)
	public List<Memo> findByTitle(String title);

	@Transactional(readOnly = true)
	public List<Memo> findByCategory(String category);

	@Transactional(readOnly = true)
	public List<Memo> findByBookName(String bookName);
	
	@Transactional(readOnly = true)
	public List<Memo> findByAccount(Account account);
	
}
