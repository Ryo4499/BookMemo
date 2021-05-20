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
		jdbcTemplate.update("INSERT INTO memos(title,content,category,book_name,user_id) VALUES(?,?,?,?,?)", memo.getTitle(), memo.getContent(), memo.getCategory(), memo.getBookName(),
				memo.getUserId());
	}

	@Override
	public List<Memo> getAll() {
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList("SELECT * FROM memos");
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
	public int updateMemo(Memo memo) {
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		return jdbcTemplate.update("UPDATE memos SET title=?,content=?,category=?,book_name=?,update_date=?",memo.getTitle(),memo.getContent(),memo.getCategory(),memo.getBookName(),updateDate);
	}

	@Override
	public int deleteMemo(Memo memo) {
		return jdbcTemplate.update("DELETE FROM memos WHERE memo_id = ?",memo.getMemoId());
	}

	@Override
	public Memo findById(long id) {
		Map<String, Object> map = jdbcTemplate.queryForMap("SELECT * FROM memos WHERE memo_id = ?",id);
		Memo memo = new Memo();
		memo.setMemoId((long)map.get("memo_id"));
		memo.setTitle((String)map.get("title"));
		memo.setContent((String)map.get("content"));
		memo.setCategory((String)map.get("category"));
		memo.setBookName((String)map.get("book_name"));
		memo.setUserId((int)map.get("user_id"));
		memo.setCreatedDate(((Timestamp)map.get("created_date")).toLocalDateTime());
		memo.setUpdatedDate(((Timestamp)map.get("updated_date")).toLocalDateTime());
		return null;
	}

}
