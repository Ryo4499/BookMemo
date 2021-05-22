package com.herokuapp.bookmemo4444.memo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	private final MemoService memoService;

	@Autowired
	public MemoController(MemoService memoService) {
		this.memoService = memoService;
	}

	@GetMapping("/")
	public String getMemoListPage(MemoForm memoForm, Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.getAttribute("userId");
		List<Memo> memoList = memoService.getFirstSix();
		List<Memo> categoryList = memoService.getAllCategory();
		System.out.println("memoList");
		memoList.forEach(System.out::println);
		System.out.println("\n\ncategoryList");
		categoryList.forEach(System.out::println);
		model.addAttribute("page",0);
		model.addAttribute("memoList", memoList);
		model.addAttribute("categoryList", categoryList);
		return "memo/memo-list";
	}

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

	@GetMapping("/create")
	public String getMemoCreatePage() {
		return "memo/memo-create";
	}

	@PostMapping("/create")
	public String postMemoCreatePage(@Validated MemoForm createMomoForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		return "memo/memo-create";
	}

	@GetMapping("/details/{memoId}")
	public String getMemoDetailsPage(MemoForm MemoForm, @PathVariable long memoId, Model model) {
		Memo memo = memoService.findById(memoId);
		model.addAttribute("memoId",memoId);
		model.addAttribute("memo", memo);
		return "memo/memo-details";
	}

	@PutMapping("/details/{memoId}")
	public String putMemoUpdatePage(MemoForm memoForm, @PathVariable long memoId, Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		memoService.update(makeMemo(memoForm, memoId));
		redirectAttributes.addAttribute("sessionId", session.getId());
		return "redirect:/memo/memo-details";
	}

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

	private Memo makeMemo(MemoForm memoForm, long memoId) {
		Memo memo = new Memo();
		if (memoId != 0) {
			memo.setMemoId(memoId);
		}
		memo.setTitle(memoForm.getTitle());
		memo.setContent(memo.getContent());
		memo.setCategory(memo.getCategory());
		memo.setBookName(memo.getBookName());
		return memo;
	}
}
