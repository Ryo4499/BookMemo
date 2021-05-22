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
import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.service.MemoService;
import com.herokuapp.bookmemo4444.service.UserService;

@Controller
@RequestMapping("/memo")
public class MemoController {

	private final MemoService memoService;
	private final UserService userService;

	@Autowired
	public MemoController(MemoService memoService, UserService userService) {
		this.memoService = memoService;
		this.userService = userService;
	}

	@GetMapping("/")
	public String getMemoListPage(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.getAttribute("userId");
		List<Memo> memoList = memoService.getFirstSix();
		List<Memo> categoryList = memoService.getAllCategory();
		System.out.println("memoList");
		memoList.forEach(System.out::println);
		System.out.println("\n\ncategoryList");
		categoryList.forEach(System.out::println);
		model.addAttribute("page", 0);
		model.addAttribute("memoList", memoList);
		model.addAttribute("categoryList", categoryList);
		return "memo/memo-list";
	}

	@GetMapping("/{page}")
	public String getMemoListPageNation(@PathVariable("page") int page, Model model) {
		List<Memo> memoList = memoService.getNextSix(page);
		List<Memo> categoryList = memoService.getAllCategory();
		model.addAttribute("page", page);
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
