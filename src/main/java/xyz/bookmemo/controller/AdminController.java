package xyz.bookmemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Memo;
import xyz.bookmemo.repository.AccountRepository;
import xyz.bookmemo.repository.MemoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

  private final AccountRepository accountRepository;
  private final MemoRepository memoRepository;

  @Autowired
  public AdminController(AccountRepository accountRepository, MemoRepository memoRepository) {
    this.accountRepository = accountRepository;
    this.memoRepository = memoRepository;
  }

  @GetMapping("")
  public String getAllAdminPage(Model model) {
    final List<Account> accounts = accountRepository.findAll();
    model.addAttribute("accountList", accounts);

    return "admin/all-accounts";
  }

  @GetMapping("/delete")
  public String getDeleteAccountConfirmPage(
      @RequestParam HashMap<String, String> params,
      Model model,
      RedirectAttributes redirectAttributes) {
    final Long accountId = Long.parseLong(params.get("accountId"));
    final Optional<Account> optional = accountRepository.findById(accountId);
    if (optional.isPresent()) {
      final Account account = optional.get();
      model.addAttribute("accountId", accountId);
      model.addAttribute("account", account);
      return "admin/account-delete-confirm";
    }
    redirectAttributes.addFlashAttribute("message", "アカウントが見つかりません");
    return "redirect:/admin";
  }

  @PostMapping("/delete")
  public String postDeleteAccount(
      @RequestParam HashMap<String, String> params,
      RedirectAttributes redirectAttributes,
      SessionStatus sessionStatus) {
    accountRepository.deleteById(Long.parseLong(params.get("id")));
    redirectAttributes.addFlashAttribute("message", "アカウントを削除しました");
    sessionStatus.setComplete();
    return "redirect:/admin";
  }

  @GetMapping("/show")
  public String getAllMemoByAccountPage(@RequestParam HashMap<String, String> params, Model model) {
    final Long accountId = Long.parseLong(params.get("accountId"));
    final String name = params.get("accountName");
    final List<Memo> memos = memoRepository.findByAccountId(accountId);
    model.addAttribute("memoList", memos);
    model.addAttribute("name", name);
    return "admin/all-memos";
  }
}
