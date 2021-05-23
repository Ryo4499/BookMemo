package com.herokuapp.bookmemo4444.memo;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.service.MemoService;
import com.herokuapp.bookmemo4444.service.UserService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	private final MemoService memoService;
	private final UserService userService;
	private final HttpSession session;
	/** １ページの表示数 */
	private final String limit = "6";

	/** ページネーションで表示するページ数 */
	private int showPageSize = 3;

	@Autowired
	public MemoController(MemoService memoService, UserService userService, HttpSession session) {
		this.memoService = memoService;
		this.userService = userService;
		this.session = session;
	}

	@GetMapping("/")
	public String getMemoListPage(Model model, Pageable pageable, @RequestParam HashMap<String, String> params,
			RedirectAttributes redirectAttributes) {
		List<Memo> categoryList = memoService.getAllCategory();
		String userId = session.getAttribute("userId").toString();
		// パラメータを設定し、現在のページを取得する
		String currentPage = params.get("page");
		// 初期表示ではパラメータを取得できないので、1ページに設定
		if (currentPage == null) {
			currentPage = "1";
		}
		// データ取得時の取得件数、取得情報の指定
		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", limit);
		search.put("page", currentPage);

		int total = 0;
		List<Memo> memoList = null;
		try {
			// データ総数を取得
			total = memoService.getMemoCount();
			// データ一覧を取得
			memoList = memoService.getMemoList(search);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		// pagination処理
		// "総数/1ページの表示数"から総ページ数を割り出す
		int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.valueOf(currentPage);
		// 表示する最初のページ番号を算出（今回は3ページ表示する設定）
		// (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
		int startPage = page - (page - 1) % showPageSize;
		// 表示する最後のページ番号を算出
		int endPage = (startPage + showPageSize - 1 > totalPage) ? totalPage : startPage + showPageSize - 1;
		model.addAttribute("userId", userId);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "memo/memo-list";
	}

	@GetMapping("/title/")
	public String getTitleMemoListPage(@RequestParam HashMap<String, String> params, Model model) {
		// TODO タイトルが見つからなかったときの例外
		List<Memo> categoryList = memoService.getAllCategory();
		String selectTitle = params.get("selectTitle");
		String currentPage = params.get("page");
		String userId = session.getAttribute("userId").toString();

		if (currentPage == null) {
			currentPage = "1";
		}

		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", limit);
		search.put("page", currentPage);

		int total = 0;
		List<Memo> memoList = null;
		try {
			total = memoService.getTitleCount(selectTitle);
			memoList = memoService.searchByTitle(search, selectTitle);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.valueOf(currentPage);
		int startPage = page - (page - 1) % showPageSize;
		int endPage = (startPage + showPageSize - 1 > totalPage) ? totalPage : startPage + showPageSize - 1;
		model.addAttribute("userId", userId);
		model.addAttribute("selectTitle", selectTitle);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "memo/memo-title";
	}

	@PostMapping("/title/")
	public String postTitleListPage(@RequestParam("title") String selectTitle, Model model) {
		List<Memo> categoryList = memoService.getAllCategory();
		HashMap<String, String> search = new HashMap<String, String>();
		String currentPage = "1";
		String userId = session.getAttribute("userId").toString();
		search.put("limit", limit);
		search.put("page", currentPage);

		int total = 0;
		List<Memo> memoList = null;
		try {
			total = memoService.getTitleCount(selectTitle);
			memoList = memoService.searchByTitle(search, selectTitle);
			memoList.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.valueOf(currentPage);
		int startPage = page - (page - 1) % showPageSize;
		int endPage = (startPage + showPageSize - 1 > totalPage) ? totalPage : startPage + showPageSize - 1;
		model.addAttribute("userId", userId);
		model.addAttribute("selectTitle", selectTitle);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "memo/memo-title";
	}

	@GetMapping("/category/")
	public String getCategoryMemoListPage(@RequestParam HashMap<String, String> params, Model model) {
		List<Memo> categoryList = memoService.getAllCategory();
		String currentPage = params.get("page");
		String selectCategory = params.get("selectCategory");
		String userId = session.getAttribute("userId").toString();
		params.forEach((k, v) -> {
			System.out.println(k);
			System.out.println(v);
		});

		if (currentPage == null) {
			currentPage = "1";
		}

		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", limit);
		search.put("page", currentPage);

		int total = 0;
		List<Memo> memoList = null;
		try {
			total = memoService.getCategoryCount(selectCategory);
			memoList = memoService.searchByCategory(search, selectCategory);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		int totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.valueOf(currentPage);
		int startPage = page - (page - 1) % showPageSize;
		int endPage = (startPage + showPageSize - 1 > totalPage) ? totalPage : startPage + showPageSize - 1;
		model.addAttribute("userId", userId);
		model.addAttribute("selectCategory", selectCategory);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "memo/memo-category";
	}

	@GetMapping("/create/")
	public String getMemoCreatePage(MemoForm memoForm, Model model) {
		return "memo/memo-create";
	}

	@PostMapping("/create/")
	public String postMemoCreatePage(@Validated MemoForm memoForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		String userId = session.getAttribute("userId").toString();
		User user = userService.findById(Integer.parseInt(userId));
		if (result.hasErrors()) {
			model.addAttribute("userId", userId);
			return "memo/memo-create";
		}
		Memo memo = makeMemo(memoForm, 0, user);
		memoService.insert(memo);
		return "redirect:/memo/";
	}

	@GetMapping("/details/{memoId}")
	public String getMemoDetailsPage(@Validated MemoForm memoForm, @PathVariable long memoId, Model model) {
		List<Memo> categoryList = memoService.getAllCategory();
		Memo memo = memoService.findById(memoId);
		memoForm.setTitle(memo.getTitle());
		memoForm.setContent(memo.getContent());
		memoForm.setCategory(memo.getCategory());
		memoForm.setBookName(memo.getBookName());
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("createdDate", memo.getCreatedDate());
		model.addAttribute("updatedDate", memo.getUpdatedDate());
		model.addAttribute("memoForm", memoForm);
		model.addAttribute("memo", memo);
		return "memo/memo-details";
	}

	@PostMapping("/details/")
	public String putMemoUpdatePage(@Validated MemoForm memoForm, BindingResult result, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		long memoId = Long.parseLong(memoForm.getMemoId());
		User user = userService.findById(userId);
		Memo memo = makeMemo(memoForm, memoId, user);
		if (result.hasErrors()) {
			return "memo/details/" + memo.getMemoId();
		}
		memoService.update(memo);
		return "redirect:/memo/";
	}

	@GetMapping("/details/delete/{memoId}")
	public String deleteMemo(@PathVariable("memoId") long memoId) {
		memoService.delete(memoId);
		return "redirect:/memo/";
	}

	private Memo makeMemo(MemoForm memoForm, long memoId, User user) {
		Memo memo = new Memo();
		if (memoId != 0) {
			memo.setMemoId(memoId);
		}
		memo.setTitle(memoForm.getTitle());
		memo.setContent(memoForm.getContent());
		memo.setCategory(memoForm.getCategory());
		memo.setBookName(memoForm.getBookName());
		memo.setUser(user);
		return memo;
	}
}
