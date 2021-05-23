package com.herokuapp.bookmemo4444.memo;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/** １ページの表示数 */
	private final String limit = "6";

	/** ページネーションで表示するページ数 */
	private int showPageSize = 3;

	@Autowired
	public MemoController(MemoService memoService, UserService userService) {
		this.memoService = memoService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String getMemoListPage(Model model, Pageable pageable, @RequestParam HashMap<String, String> params,
			RedirectAttributes redirectAttributes) {
		redirectAttributes.getAttribute("userId");
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
		model.addAttribute("memoList", memoList);
		model.addAttribute("total", total);
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "memo/memo-list";
	}

//	@GetMapping("/{page}")
//	public String getMemoListPageNation(Model model, Pageable pageable, RedirectAttributes redirectAttributes) {
//		redirectAttributes.getAttribute("userId");
//		Page<Memo> page = memoService.getPage(pageable);
//		model.addAttribute("page", page);
//		model.addAttribute("path", "/list");
//		model.addAttribute("list", page.getContent());
//		return "memo/memo-list";
//	}

	@GetMapping("/title/{title}")
	public String getTitleMemoListPage(@PathVariable("title") String title, Model model) {
		List<Memo> memoList = memoService.searchByTitle(title);
		List<Memo> categoryList = memoService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		return "memo/memo-list";
	}

	@GetMapping("/category/{categoryName}")
	public String getCategoryMemoListPage(@PathVariable("categoryName") String category, Model model) {
		List<Memo> memoList = memoService.searchByCategory(category);
		List<Memo> categoryList = memoService.getAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("memoList", memoList);
		return "memo/memo-list";
	}

	@GetMapping("/create/{userId}")
	public String getMemoCreatePage(@PathVariable("userId") int userId, MemoForm memoForm, Model model) {
		model.addAttribute(userId);
		return "memo/memo-create";
	}

	/**
	 * 
	 * @param memoForm           HTMLのinputタグとバンドされた値が入っている(title,content,category,bookName)
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/create/{userId}")
	public String postMemoCreatePage(@Validated MemoForm memoForm, @PathVariable int userId, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		User user = userService.findById(userId);
		Memo memo = makeMemo(memoForm, 0, user);
		memoService.insert(memo);
		redirectAttributes.addFlashAttribute("user_id", userId);
		return "redirect:/memo/";
	}

	/**
	 * 
	 * @param memoForm HTMLのinputタグとバンドされた値が入っている(title,content,category,bookName)
	 * @param memoId
	 * @param model
	 * @return
	 */
	@GetMapping("/details/{memoId}")
	public String getMemoDetailsPage(MemoForm memoForm, @PathVariable long memoId, Model model) {
		Memo memo = memoService.findById(memoId);
		memoForm.setTitle(memo.getTitle());
		memoForm.setContent(memo.getContent());
		memoForm.setCategory(memo.getCategory());
		memoForm.setBookName(memo.getBookName());
		model.addAttribute("createdDate", memo.getCreatedDate());
		model.addAttribute("updatedDate", memo.getUpdatedDate());
		model.addAttribute("memoForm", memoForm);
		model.addAttribute("memo", memo);
		return "memo/memo-details";
	}

	/**
	 * 
	 * @param memoId
	 * @param memoForm           HTMLのinputタグとバンドされた値が入っている(title,content,category,bookName)
	 * @param result
	 * @param model
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PutMapping("/details/{memoId}")
	public String putMemoUpdatePage(@PathVariable long memoId, @PathVariable int userId, @Validated MemoForm memoForm,
			BindingResult result, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
		User user = userService.findById(userId);
		Memo memo = makeMemo(memoForm, memoId, user);
		if (result.hasErrors()) {
			return "memo/details/" + memoId;
		}
		memoService.update(memo);
		redirectAttributes.addAttribute("userId", userId);
		return "redirect:/memo/";
	}

	/**
	 * 
	 * @param memoId
	 * @param userId
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@DeleteMapping("/details/{memoId}")
	public String deleteMemo(@PathVariable("memoId") long memoId, @PathVariable("userId") int userId,
			RedirectAttributes redirectAttributes, HttpSession session) {
		memoService.delete(memoId);
		redirectAttributes.addFlashAttribute("user_id", userId);
		return "redirect:/memo/";
	}

	@GetMapping("/test")
	public String testSelect(MemoForm memoForm, Model model) {
		List<Memo> memoList = memoService.getAll();
//		memoList.forEach(memo -> {
//			System.out.println(memo.getMemoId());
//			System.out.println(memo.getTitle());
//			System.out.println(memo.getCategory());
//		});
		model.addAttribute("memoList", memoList);
		return "memo/test2";
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
