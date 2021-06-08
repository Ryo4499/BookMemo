package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long>{
	public List<Memo> findByTitle(String title);

	public List<Memo> findByCategory(String category);

	public List<Memo> findByBookName(String bookName);
	
	
}
