package com.herokuapp.bookmemo4444.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.Account;
import com.herokuapp.bookmemo4444.entity.Memo;
import com.herokuapp.bookmemo4444.repository.AccountRepository;
import com.herokuapp.bookmemo4444.repository.MemoDao;
import com.herokuapp.bookmemo4444.repository.MemoRepository;

@Controller
@RequestMapping("/admin")
@SessionAttributes({"accountId","memoId"})
public class AdminController {
	private final AccountRepository accountRepository;
	private final MemoRepository memoRepository;
	private final MemoDao memoDao;

	@Autowired
	public AdminController(AccountRepository accountRepository, MemoRepository memoRepository,MemoDao memoDao) {
		this.accountRepository = accountRepository;
		this.memoRepository = memoRepository;
		this.memoDao=memoDao;
	}

	@GetMapping("")
	public String getAllAdminPage(Model model) {
		List<Account> accounts = accountRepository.findAll();
		model.addAttribute("accountList",accounts);
		
		return "admin/show-all-account";
	}
	@GetMapping("/delete")
	public String getDeleteAccountConfirmPage(@RequestParam HashMap<String, String> params,Model model,RedirectAttributes redirectAttributes) {
		Long accountId= Long.parseLong(params.get("accountId"));
		Optional<Account> optional = accountRepository.findById(accountId);
		if(optional.isPresent()) {
			Account account = optional.get();
			model.addAttribute("accountId",accountId);
			model.addAttribute("account",account);
			return "admin/delete-account-confirm";			
		}
		redirectAttributes.addFlashAttribute("message","アカウントが見つかりません");
		return "redirect:/admin";
	}
	
	@PostMapping("/delete")
	public String postDeleteAccount(Model model,RedirectAttributes redirectAttributes,SessionStatus sessionStatus) {
		accountRepository.deleteById((Long) model.getAttribute("accountId"));
		redirectAttributes.addFlashAttribute("message","アカウントを削除しました");
		sessionStatus.setComplete();
		return "redirect:/admin";
	}

	@GetMapping("/show")
	public String getAllMemoByAccountPage(@RequestParam HashMap<String, String> params,Model model) {
		Long accountId= Long.parseLong(params.get("accountId"));
		String name=params.get("accountName");
		List<Memo> memos = memoRepository.findByAccountId(accountId);
		model.addAttribute("memoList",memos);
		model.addAttribute("name",name);
		return "admin/all-memo-page";
	}

	@GetMapping("/memodelete")
	public String getDeleteMemoConfirmPage(@RequestParam HashMap<String, String> params,Model model,RedirectAttributes redirectAttributes) {
		long memoId = Long.parseLong(params.get("memoId"));
		Optional<Memo> optional = memoRepository.findById(memoId);
		if (optional.isPresent()) {
			Memo memo = optional.get();
			System.out.println(memo.getMemoId());
			model.addAttribute("memoId",memoId);
			model.addAttribute("memo", memo);
			return "admin/delete-memo-confirm";
				
		}
		redirectAttributes.addFlashAttribute("message","メモが見つかりません");
		return "redirect:/admin/show";
	}
	
	@PostMapping("/memodelete")
	public String postDeleteMemo(Model model,RedirectAttributes redirectAttributes,SessionStatus sessionStatus) {
		long memoId = (long) model.getAttribute("memoId");
		Optional<Memo> optional = memoRepository.findById(memoId);
		Memo memo = optional.get();
		Optional<Account> account = accountRepository.findById((Long) model.getAttribute("accountId"));
		if(account.isPresent()) {
			System.err.println(memoId+"ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"+memoId);
			memo.setAccount(account.get());
			memoRepository.delete(memo);
			redirectAttributes.addFlashAttribute("message", "メモを削除しました");
			sessionStatus.setComplete();
			return "redirect:/admin/show";	
		}
		return "redirect:/";
	}
	
}
