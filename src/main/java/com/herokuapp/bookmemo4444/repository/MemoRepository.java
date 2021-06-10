package com.herokuapp.bookmemo4444.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
@Transactional(readOnly = true)
public interface MemoRepository extends JpaRepository<Memo, Long> {
}
