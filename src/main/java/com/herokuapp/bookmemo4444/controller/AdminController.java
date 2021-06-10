package com.herokuapp.bookmemo4444.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.AccountRepository;
import com.herokuapp.bookmemo4444.repository.MemoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	private final AccountRepository accountRepository;
	private final MemoRepository memoRepository;
	private final HttpServletRequest req;

	@Autowired
	public AdminController(AccountRepository accountRepository, MemoRepository memoRepository,HttpServletRequest req) {
		this.accountRepository = accountRepository;
		this.memoRepository = memoRepository;
		this.req=req;
	}

	@GetMapping("")
	public String getAllAdminPage(Model model) {
		List<Account> accounts = accountRepository.findAll();
		model.addAttribute("accountList",accounts);
		req.removeAttribute("accountId");
		return "admin/show-all-account";
	}
	@GetMapping("/delete")
	public String getDeleteAccountConfirmPage(@RequestParam HashMap<String, String> params,Model model,RedirectAttributes redirectAttributes) {
		Long accountId= Long.parseLong(params.get("accountId"));
		req.getSession().setAttribute("accountId", accountId);
		Optional<Account> optional = accountRepository.findById(accountId);
		if(!optional.isPresent()) {
			redirectAttributes.addFlashAttribute("message","アカウントが見つかりません");
			return "redirect:/admin";		
		}
		Account account = optional.get();
		model.addAttribute("account",account);
		return "admin/delete-account-confirm";		
	}
	
	@PostMapping("/delete")
	public String postDeleteAccount(@RequestParam HashMap<String, String> params,RedirectAttributes redirectAttributes) {
		accountRepository.deleteById(Long.parseLong(req.getSession().getAttribute("accountId").toString()));
		redirectAttributes.addFlashAttribute("message","アカウントを削除しました");
		req.getSession().removeAttribute("accountId");
		return "redirect:/admin";
	}

	@GetMapping("/show")
	public String getAllMemoByAccountPage(@RequestParam HashMap<String, String> params,Model model) {
		req.removeAttribute("memoId");
		Long accountId= Long.parseLong(params.get("accountId"));
		String name=params.get("accountName");
		List<Memo> memos = memoRepository.findByAccountId(accountId);
		model.addAttribute("memoList",memos);
		model.addAttribute("name",name);
		return "admin/all-memo-page";
	}

	@GetMapping("/show/delete")
	public String getDeleteMemoConfirmPage(@RequestParam HashMap<String, String> params,Model model,RedirectAttributes redirectAttributes) {
		Long memoId = Long.parseLong(params.get("memoId"));
		req.setAttribute("memoId", memoId);
		Optional<Memo> optional = memoRepository.findById(memoId);
		if (!optional.isPresent()) {
			redirectAttributes.addFlashAttribute("message","メモが見つかりません");
			return "redirect:/admin/show";
		}
		Memo memo = optional.get();
		System.out.println(memo.getMemoId());
		model.addAttribute("memo", memo);
		return "admin/delete-memo-confirm";
	}
	
	@PostMapping("/show/delete")
	public String postDeleteMemo(Model model,RedirectAttributes redirectAttributes) {
		System.err.println();
		memoRepository.deleteByMemoId(Long.parseLong(req.getSession().getAttribute("memoId").toString()));
		redirectAttributes.addFlashAttribute("message", "メモを削除しました");
		return "redirect:/admin/show";
	}
	
}
