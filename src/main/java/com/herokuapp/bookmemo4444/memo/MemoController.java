package com.herokuapp.bookmemo4444.memo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MemoController {
	@GetMapping("/memo-list")
	public String getMemoListPage(@ModelAttribute("complete")String complete) {
		return "memo/memo-list";
	}
}
