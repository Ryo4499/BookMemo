package com.herokuapp.bookmemo4444.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.herokuapp.bookmemo4444.form.MemoForm;
import com.herokuapp.bookmemo4444.repository.MemoRepository;
import com.herokuapp.bookmemo4444.security.CustomSecurityAccount;
import com.herokuapp.bookmemo4444.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	/** １ページの表示数 */
	private final String limit = "6";

	/** ページネーションで表示するページ数 */
	private int showPageSize = 3;

	private final MemoService memoService;

	private final MemoRepository memoRepository;

	@Autowired
	public MemoController(MemoService memoService, MemoRepository memoRepository) {
		this.memoService = memoService;
		this.memoRepository = memoRepository;
	}

	@GetMapping("/")
	public String getMemoListPage(Model model, @RequestParam HashMap<String, String> params,
			@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
			RedirectAttributes redirectAttributes) {
		List<String> categoryList = memoService.findDistinctCategoryByAccount(customSecurityAccount);
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

		Long total = 0L;
		List<Memo> memoList = null;
		try {
			// データ総数を取得
			total = memoService.countMemoIdByAccount(customSecurityAccount);
			// データ一覧を取得
			memoList = memoService.noConditionSearch(customSecurityAccount, search);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		// pagination処理
		// "総数/1ページの表示数"から総ページ数を割り出す
		long totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.parseInt(currentPage);
		// 表示する最初のページ番号を算出（今回は3ページ表示する設定）
		// (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
		int startPage = page - (page - 1) % showPageSize;
		// 表示する最後のページ番号を算出
		long endPage = startPage + showPageSize - 1 > totalPage ? totalPage : startPage + showPageSize - 1;

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
	public String getTitleMemoListPage(@RequestParam HashMap<String, String> params,
			@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
		// TODO タイトルが見つからなかったときの例外
		List<String> categoryList = memoService.findDistinctCategoryByAccount(customSecurityAccount);
		String selectTitle = params.get("selectTitle");
		String currentPage = params.get("page");

		if (currentPage == null) {
			currentPage = "1";
		}

		HashMap<String, String> search = new HashMap<String, String>();
		search.put("limit", limit);
		search.put("page", currentPage);

		Long total = 0L;
		List<Memo> memoList = null;
		try {
			total = memoService.countTitleByTitleAndAccount(selectTitle, customSecurityAccount);
			memoList = memoService.searchTitle(selectTitle, customSecurityAccount, search);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/faital";
		}

		long totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.parseInt(currentPage);
		int startPage = page - (page - 1) % showPageSize;
		long endPage = startPage + showPageSize - 1 > totalPage ? totalPage : startPage + showPageSize - 1;

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
	public String getCategoryMemoListPage(@RequestParam HashMap<String, String> params,
			@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
		List<String> categoryList = memoService.findDistinctCategoryByAccount(customSecurityAccount);
		String currentPage = params.get("page");
		String selectCategory = params.get("selectCategory");

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

		Long total = 0L;
		List<Memo> memoList = null;
		try {
			total = memoService.countCategoryByCategoryAndAccount(selectCategory, customSecurityAccount);
			memoList = memoService.searchCategory(selectCategory, customSecurityAccount, search);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("eeeeeeeeeeeeee" + total);
		memoList.forEach(memo -> System.out.println(memo.getTitle()));

		long totalPage = (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
		int page = Integer.parseInt(currentPage);
		int startPage = page - (page - 1) % showPageSize;
		long endPage = startPage + showPageSize - 1 > totalPage ? totalPage : startPage + showPageSize - 1;

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
//TODO memo save
		if (result.hasErrors()) {

			return "memo/memo-create";
		}

		return "redirect:/memo/";
	}

	@GetMapping("/details/{memoId}")
	public String getMemoDetailsPage(@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
			@PathVariable long memoId, Model model) {
		List<String> categoryList = memoService.findDistinctCategoryByAccount(customSecurityAccount);
		Optional<Memo> optionalMemo = memoRepository.findById(memoId);
		if (optionalMemo.isPresent()) {
			Memo memo = optionalMemo.get();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("createdDate", memo.getCreatedDate());
			model.addAttribute("updatedDate", memo.getUpdatedDate());
			model.addAttribute("memoForm", makeMemoForm(memo));
		}
		return "memo/memo-details";
	}

	@PostMapping("/details/")
	public String putMemoUpdatePage(@Validated MemoForm memoForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		// return "memo/details/" + memo.getMemoId();

		return "redirect:/memo/";
	}

	@GetMapping("/delete/")
	public String deleteConfirmPage(MemoForm memoForm,
			@AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
		List<String> categoryList = memoService.findDistinctCategoryByAccount(customSecurityAccount);
		Optional<Memo> optional = memoRepository.findById(memoForm.getMemoId());
		// TODO メモを取ってきて､確認画面に反映
		if (optional.isPresent()) {
			Memo memo = optional.get();
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("memo", memo);
		}

		return "memo/delete-confirm";
	}

	@PostMapping("/delete/")
	public String deleteMemo(@PathVariable("memoId") long memoId) {
		memoRepository.deleteById(memoId);
		return "redirect:/memo/";
	}

	private MemoForm makeMemoForm(Memo memo) {
		MemoForm memoForm = new MemoForm();
		memoForm.setMemoId(memo.getMemoId());
		memoForm.setTitle(memo.getTitle());
		memoForm.setContent(memo.getContent());
		memoForm.setCategory(memo.getCategory());
		memoForm.setBookName(memo.getBookName());
		return memoForm;
	}
}
