package com.herokuapp.bookmemo4444.memo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@GetMapping("/delete/{memoId}")
	public String testDelete(@PathVariable("memoId") long memoId, Model model) {
		System.out.println(memoId);
		memoService.delete(memoId);
		return "memo/test2";
	}

	@GetMapping("/")
	public String getMemoListPage(MemoForm memoForm, Model model) {
		List<Memo> memoList = memoService.getAll();
		memoList.forEach(memo -> {
			System.out.println(memo.getMemoId());
			System.out.println(memo.getTitle());
			System.out.println(memo.getCategory());
		});
		model.addAttribute("memoList", memoList);
		return "memo/memo-list";
	}

	@GetMapping("/details/{memoId}")
	public String getMemoDetailsPage(MemoForm MemoForm, @PathVariable int id, Model model) {
		Memo memo = memoService.findById(id);
		model.addAttribute("memo", memo);
		return "memo/memo-details";
	}

	@GetMapping("/create")
	public String getMemoCreatePage() {
		return "memo/memo-create";
	}

	@PutMapping("/create")
	public String putMemoCreatePage(@Validated MemoForm createMomoForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		return "memo/memo-create";
	}

	@PutMapping("/details")
	public String putMemoUpdatePage() {
		return "memo/memo-details";
	}

	@DeleteMapping("/details")
	public String deleteMemoPage() {
		return "memo/memo-list";
	}

	@GetMapping("/index")
	public String index(Model model) {
		List<Memo> memoList = memoService.getAll();
		model.addAttribute("memoList", memoList);
		return "memo/index";
	}

}
