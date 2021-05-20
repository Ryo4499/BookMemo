package com.herokuapp.bookmemo4444.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemoDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertMemo(Memo memo) {
		String sql = "INSERT INTO memos(title,content,category,book_name,user_id) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql, memo.getTitle(), memo.getContent(), memo.getCategory(), memo.getBookName(),
				memo.getUserId());
	}

	@Override
	public List<Memo> getAll() {
		String sql = "SELECT * FROM memos";
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList(sql);
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map->{
			Memo memo = new Memo();
			memo.setMemoId((long)map.get("memo_id"));
			memo.setTitle((String)map.get("title"));
			memo.setContent((String)map.get("content"));
			memo.setCategory((String)map.get("category"));
			memo.setBookName((String)map.get("book_name"));
			memo.setUserId((int)map.get("user_id"));
			memo.setCreatedDate(((Timestamp)map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp)map.get("updated_date")).toLocalDateTime());
			memoList.add(memo);
		});
		return memoList;
	}

	@Override
	public void updateMemo(Memo memo) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteMemo(Memo memo) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
