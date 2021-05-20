package com.herokuapp.bookmemo4444.memo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	private final MemoService memoService;
	
	@Autowired
	public MemoController(MemoService memoService) {
		this.memoService=memoService;
	}
	
	@GetMapping("/memo-list")
	public String getMemoListPage(@ModelAttribute("complete")String complete) {
		return "memo/memo-list";
	}
	
	@GetMapping("/memo-details")
	public String getMemoDetailsPage() {
		return "memo/memo-details";
	}
	
	@GetMapping("/memo-create")
	public String getMemoCreatePage() {
		return "memo/memo-create";
	}
	
	@PutMapping("/memo-create")
	public String putMemoCreatePage() {
		return "memo/memo-create";
	}
	
	@PutMapping("/memo-details")
	public String putMemoUpdatePage() {
		return "memo/memo-details";
	}
	
	@DeleteMapping("/memo-details")
	public String deleteMemoPage() {
		return "memo/memo-list";
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		List<Memo> memoList = memoService.getAll();
		model.addAttribute("memoList",memoList);
		return "memo/index";
	}
}
