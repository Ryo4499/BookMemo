package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public interface MemoRepository extends CrudRepository<Memo, Long> {
}
