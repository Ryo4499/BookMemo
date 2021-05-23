package com.herokuapp.bookmemo4444.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.entity.User;

@Repository
public class MemoDaoImpl implements MemoDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public MemoDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertMemo(Memo memo) {
		jdbcTemplate.update("INSERT INTO memos(title,content,category,book_name,user_id) VALUES(?,?,?,?,?)",
				memo.getTitle(), memo.getContent(), memo.getCategory(), memo.getBookName(), memo.getUser().getUserId());
	}

	@Override
	public List<Memo> getAll() {
		String sql = "SELECT memo_id,title,content,category,book_name,users.user_id," + "created_date,updated_date "
				+ "FROM memos INNER JOIN users " + "ON memos.user_id = users.user_id";
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList(sql);
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map -> {
			Memo memo = new Memo();
			memo.setMemoId((long) map.get("memo_id"));
			memo.setTitle((String) map.get("title"));
			memo.setContent((String) map.get("content"));
			memo.setCategory((String) map.get("category"));
			memo.setBookName((String) map.get("book_name"));
			memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			memo.setUser(user);
			memoList.add(memo);
		});
		return memoList;
	}

	@Override
	public int updateMemo(Memo memo) {
		Timestamp updateDate = new Timestamp(System.currentTimeMillis());
		return jdbcTemplate.update("UPDATE memos SET title=?,content=?,category=?,book_name=?,updated_date=?",
				memo.getTitle(), memo.getContent(), memo.getCategory(), memo.getBookName(), updateDate);
	}

	@Override
	public Memo findById(long id) {
		String sql = "SELECT memo_id,title,content,category,book_name,users.user_id,created_date,updated_date FROM memos INNER JOIN users ON memos.user_id = users.user_id WHERE memo_id = ? ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql, id);
		Memo memo = new Memo();
		memo.setMemoId((long) map.get("memo_id"));
		memo.setTitle((String) map.get("title"));
		memo.setContent((String) map.get("content"));
		memo.setCategory((String) map.get("category"));
		memo.setBookName((String) map.get("book_name"));
		memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
		memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

		User user = new User();
		user.setUserId((int) map.get("user_id"));
		user.setUserName((String) map.get("user_name"));
		user.setUserEmail((String) map.get("user_email"));
		user.setUserPassword((String) map.get("user_password"));
		user.setRememberUser((String) map.get("remember_user"));
		memo.setUser(user);
		return memo;
	}

	@Override
	public List<Memo> getAllCategory() {
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList("SELECT DISTINCT ON (category) * FROM memos");
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map -> {
			Memo memo = new Memo();
			memo.setMemoId((long) map.get("memo_id"));
			memo.setTitle((String) map.get("title"));
			memo.setContent((String) map.get("content"));
			memo.setCategory((String) map.get("category"));
			memo.setBookName((String) map.get("book_name"));
			memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			memo.setUser(user);

			memoList.add(memo);
		});
		return memoList;
	}

	@Override
	public int deleteMemo(long id) {
		return jdbcTemplate.update("DELETE FROM memos WHERE memo_id = ?", id);
	}

	@Override
	public List<Memo> searchByCategory(HashMap<String, String> search, String category) {
		String sql = "SELECT * FROM memos WHERE category = ? ORDER BY created_date DESC,memo_id DESC OFFSET ? FETCH FIRST ? ROWS ONLY";
		int limit = Integer.valueOf(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList(sql, category, page, limit);
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map -> {
			Memo memo = new Memo();
			memo.setMemoId((long) map.get("memo_id"));
			memo.setTitle((String) map.get("title"));
			memo.setContent((String) map.get("content"));
			memo.setCategory((String) map.get("category"));
			memo.setBookName((String) map.get("book_name"));
			memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			memo.setUser(user);

			memoList.add(memo);
		});

		return memoList;
	}

	@Override
	public List<Memo> searchByTitle(HashMap<String, String> search, String title) {
		String sql = "SELECT * FROM memos WHERE title = ? ORDER BY created_date DESC,memo_id DESC OFFSET ? FETCH FIRST ? ROWS ONLY";
		int limit = Integer.valueOf(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList(sql, title, page, limit);
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map -> {
			Memo memo = new Memo();
			memo.setMemoId((long) map.get("memo_id"));
			memo.setTitle((String) map.get("title"));
			memo.setContent((String) map.get("content"));
			memo.setCategory((String) map.get("category"));
			memo.setBookName((String) map.get("book_name"));
			memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			memo.setUser(user);

			memoList.add(memo);
		});

		return memoList;
	}

	@Override
	public int getMemoCount() {
		String sql = "SELECT COUNT(memo_id) FROM memos";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int getCategoryCount() {
		String sql = "SELECT COUNT(DISTINCT category) FROM memos";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public int getTitleCount() {
		String sql = "SELECT COUNT(DISTINCT title) FROM memos";
		int count = jdbcTemplate.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public List<Memo> getMemoList(HashMap<String, String> search) {
		String sql = "SELECT * FROM memos ORDER BY created_date DESC,memo_id DESC OFFSET ? FETCH FIRST ? ROWS ONLY";
		int limit = Integer.valueOf(search.get("limit"));
		int page = Integer.valueOf(search.get("page")) - 1;
		List<Map<String, Object>> tmpList = jdbcTemplate.queryForList(sql, page, limit);
		List<Memo> memoList = new ArrayList<>();
		tmpList.forEach(map -> {
			Memo memo = new Memo();
			memo.setMemoId((long) map.get("memo_id"));
			memo.setTitle((String) map.get("title"));
			memo.setContent((String) map.get("content"));
			memo.setCategory((String) map.get("category"));
			memo.setBookName((String) map.get("book_name"));
			memo.setCreatedDate(((Timestamp) map.get("created_date")).toLocalDateTime());
			memo.setUpdatedDate(((Timestamp) map.get("updated_date")).toLocalDateTime());

			User user = new User();
			user.setUserId((int) map.get("user_id"));
			user.setUserName((String) map.get("user_name"));
			user.setUserEmail((String) map.get("user_email"));
			user.setUserPassword((String) map.get("user_password"));
			user.setRememberUser((String) map.get("remember_user"));
			memo.setUser(user);

			memoList.add(memo);
		});

		return memoList;

	}

}
