package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.herokuapp.bookmemo4444.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Long>{

}
