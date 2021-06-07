package com.herokuapp.bookmemo4444.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long> {
	public List<Memo> findByTitle(String title);

	public List<Memo> findByCategory(String category);

	public List<Memo> findByBookName(String bookName);
}
