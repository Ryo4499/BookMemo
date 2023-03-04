package xyz.bookmemo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Memo;
import xyz.bookmemo.form.MemoForm;
import xyz.bookmemo.repository.MemoRepository;
import xyz.bookmemo.security.CustomSecurityAccount;
import xyz.bookmemo.service.MemoService;

// TODO タイトルが見つからなかったときのエラー処理

@Controller
@RequestMapping("/memo")
public class MemoController {

  /** １ページの表示数 */
  private final String limit = "6";

  /** ページネーションで表示するページ数 */
  private final int showPageSize = 3;

  private final MemoService memoService;

  private final MemoRepository memoRepository;

  private final HttpServletRequest req;

  @Autowired
  public MemoController(
    MemoService memoService,
    MemoRepository memoRepository,
    HttpServletRequest req
  ) {
    this.memoService = memoService;
    this.memoRepository = memoRepository;
    this.req = req;
  }

  @GetMapping("")
  public String getMemoListPage(
    Model model,
    @RequestParam HashMap<String, String> params,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    RedirectAttributes redirectAttributes
  ) {
    req.getSession().removeAttribute("memoId");

    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );

    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    // パラメータを設定し、現在のページを取得する
    String currentPage = params.get("page");
    // 初期表示ではパラメータを取得できないので、1ページに設定
    if (currentPage == null) currentPage = "1";
    // データ取得時の取得件数、取得情報の指定
    final HashMap<String, String> search = new HashMap<>();
    search.put("limit", limit);
    search.put("page", currentPage);

    Long total = 0L;
    List<Memo> memoList = null;
    try {
      // データ総数を取得
      total = memoService.countMemoIdByAccount(customSecurityAccount);
      // データ一覧を取得
      memoList = memoService.noConditionSearch(customSecurityAccount, search);
    } catch (final Exception e) {
      e.printStackTrace();
      return "error/faital";
    }

    // pagination処理
    // "総数/1ページの表示数"から総ページ数を割り出す
    final long totalPage =
      (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
    final int page = Integer.parseInt(currentPage);
    // 表示する最初のページ番号を算出（今回は3ページ表示する設定）
    // (例)1,2,3ページのstartPageは1。4,5,6ページのstartPageは4
    final int startPage = page - (page - 1) % showPageSize;
    // 表示する最後のページ番号を算出
    final long endPage = startPage + showPageSize - 1 > totalPage
      ? totalPage
      : startPage + showPageSize - 1;

    model.addAttribute("categoryList", categoryList);
    model.addAttribute("memoList", memoList);
    model.addAttribute("total", total);
    model.addAttribute("page", page);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "memo/memos-top";
  }

  @GetMapping("/title")
  public String getTitleMemoListPage(
    @RequestParam HashMap<String, String> params,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model
  ) {
    req.getSession().removeAttribute("memoId");

    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );

    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    final String selectTitle = params.get("selectTitle");
    String currentPage = params.get("page");
    if (currentPage == null) currentPage = "1";

    final HashMap<String, String> search = new HashMap<>();
    search.put("limit", limit);
    search.put("page", currentPage);

    Long total = 0L;
    List<Memo> memoList = null;
    try {
      total =
        memoService.countTitleByTitleAndAccount(
          selectTitle,
          customSecurityAccount
        );
      memoList =
        memoService.searchTitle(selectTitle, customSecurityAccount, search);
    } catch (final Exception e) {
      e.printStackTrace();
      return "error/faital";
    }

    final long totalPage =
      (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
    final int page = Integer.parseInt(currentPage);
    final int startPage = page - (page - 1) % showPageSize;
    final long endPage = startPage + showPageSize - 1 > totalPage
      ? totalPage
      : startPage + showPageSize - 1;

    model.addAttribute("selectTitle", selectTitle);
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("memoList", memoList);
    model.addAttribute("total", total);
    model.addAttribute("page", page);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "memo/search-title-memos";
  }

  @GetMapping("/category")
  public String getCategoryMemoListPage(
    @RequestParam HashMap<String, String> params,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model
  ) {
    req.getSession().removeAttribute("memoId");

    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );

    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    String currentPage = params.get("page");
    final String selectCategory = params.get("selectCategory");

    if (currentPage == null) currentPage = "1";

    final HashMap<String, String> search = new HashMap<>();
    search.put("limit", limit);
    search.put("page", currentPage);

    Long total = 0L;
    List<Memo> memoList = null;
    try {
      total =
        memoService.countCategoryByCategoryAndAccount(
          selectCategory,
          customSecurityAccount
        );
      memoList =
        memoService.searchCategory(
          selectCategory,
          customSecurityAccount,
          search
        );
    } catch (final Exception e) {
      e.printStackTrace();
    }

    final long totalPage =
      (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
    final int page = Integer.parseInt(currentPage);
    final int startPage = page - (page - 1) % showPageSize;
    final long endPage = startPage + showPageSize - 1 > totalPage
      ? totalPage
      : startPage + showPageSize - 1;

    model.addAttribute("selectCategory", selectCategory);
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("memoList", memoList);
    model.addAttribute("total", total);
    model.addAttribute("page", page);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "memo/search-category-memos";
  }

  @GetMapping("/book")
  public String getBookMemoListPage(
    @RequestParam HashMap<String, String> params,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model
  ) {
    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );
    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    String currentPage = params.get("page");
    final String selectBook = params.get("selectBook");

    if (currentPage == null) currentPage = "1";

    final HashMap<String, String> search = new HashMap<>();
    search.put("limit", limit);
    search.put("page", currentPage);

    Long total = 0L;
    List<Memo> memoList = null;
    try {
      total =
        memoService.countBookNameByBookNameAndAccount(
          selectBook,
          customSecurityAccount
        );
      memoList =
        memoService.searchBookName(selectBook, customSecurityAccount, search);
    } catch (final Exception e) {
      e.printStackTrace();
    }

    final long totalPage =
      (total + Integer.valueOf(limit) - 1) / Integer.valueOf(limit);
    final int page = Integer.parseInt(currentPage);
    final int startPage = page - (page - 1) % showPageSize;
    final long endPage = startPage + showPageSize - 1 > totalPage
      ? totalPage
      : startPage + showPageSize - 1;

    model.addAttribute("selectBook", selectBook);
    model.addAttribute("categoryList", categoryList);
    model.addAttribute("memoList", memoList);
    model.addAttribute("total", total);
    model.addAttribute("page", page);
    model.addAttribute("totalPage", totalPage);
    model.addAttribute("startPage", startPage);
    model.addAttribute("endPage", endPage);
    return "memo/search-book-name-memos";
  }

  @GetMapping("/create")
  public String getMemoCreatePage(
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model
  ) {
    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );

    model.addAttribute("memoForm", new MemoForm());
    model.addAttribute("categoryList", categoryList);
    return "memo/memo-create";
  }

  @PostMapping("/create")
  public String postMemoCreatePage(
    @Validated MemoForm memoForm,
    BindingResult result,
    Model model,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    RedirectAttributes redirectAttributes
  ) {
    if (result.hasErrors()) {
      model.addAttribute("memoForm", memoForm);
      return "memo/memo-create";
    }
    final Memo memo = makeMemo(memoForm, customSecurityAccount);
    memo.setCreatedDate(new Date());
    memo.setUpdatedDate(new Date());
    memoRepository.save(memo);
    redirectAttributes.addFlashAttribute("success", "登録が完了しました｡");
    return "redirect:/memo/";
  }

  @GetMapping("/details")
  public String getMemoDetailsPage(
    @RequestParam HashMap<String, String> params,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model,
    HttpServletRequest req
  ) {
    final long memoId = Long.parseLong(params.get("memoId"));
    req.getSession().setAttribute("memoId", memoId);
    if (isAdmin(customSecurityAccount)) model.addAttribute(
      "admin",
      "管理者画面へ"
    );

    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    final Optional<Memo> optionalMemo = memoRepository.findById(memoId);
    if (optionalMemo.isPresent()) {
      final Memo memo = optionalMemo.get();
      model.addAttribute("categoryList", categoryList);
      model.addAttribute("createdDate", memo.getCreatedDate());
      model.addAttribute("updatedDate", memo.getUpdatedDate());
      model.addAttribute("memoForm", makeMemoForm(memo));
    }
    return "memo/memo-details";
  }

  @PostMapping("/details")
  public String putMemoUpdatePage(
    @Validated MemoForm memoForm,
    BindingResult result,
    Model model,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    RedirectAttributes redirectAttributes
  ) {
    final long memoId = (long) req.getSession().getAttribute("memoId");
    if (result.hasErrors()) {
      model.addAttribute("memoForm", memoForm);
      return "memo/memo-details";
    }
    final Memo memo = makeMemo(memoForm, customSecurityAccount);
    memo.setUpdatedDate(new Date());
    memo.setCreatedDate(memoRepository.findByMemoId(memoId));
    memoRepository.save(memo);
    redirectAttributes.addFlashAttribute("success", "更新が完了しました｡");
    req.getSession().removeAttribute("memoId");
    return "redirect:/memo/";
  }

  @GetMapping("/delete")
  public String deleteConfirmPage(
    MemoForm memoForm,
    @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
    Model model
  ) {
    final long memoId = (long) req.getSession().getAttribute("memoId");

    final List<String> categoryList = memoService.findDistinctCategoryByAccount(
      customSecurityAccount
    );
    final Optional<Memo> optional = memoRepository.findById(memoId);
    if (optional.isPresent()) {
      final Memo memo = optional.get();
      model.addAttribute("categoryList", categoryList);
      model.addAttribute("memo", memo);
    }

    return "memo/memo-delete-confirm";
  }

  @PostMapping("/delete")
  public String deleteMemo(RedirectAttributes redirectAttributes) {
    final long memoId = (long) req.getSession().getAttribute("memoId");
    memoRepository.deleteById(memoId);
    req.getSession().removeAttribute("memoId");
    redirectAttributes.addFlashAttribute("success", "削除が完了しました｡");
    return "redirect:/memo/";
  }

  private MemoForm makeMemoForm(Memo memo) {
    final MemoForm memoForm = new MemoForm();
    memoForm.setMemoId(memo.getMemoId());
    memoForm.setTitle(memo.getTitle());
    memoForm.setContent(memo.getContent());
    memoForm.setCategory(memo.getCategory());
    memoForm.setBookName(memo.getBookName());
    return memoForm;
  }

  private Memo makeMemo(
    MemoForm memoForm,
    CustomSecurityAccount customSecurityAccount
  ) {
    final Memo memo = new Memo();
    memo.setMemoId(memoForm.getMemoId());
    memo.setTitle(memoForm.getTitle());
    memo.setContent(memoForm.getContent());
    memo.setCategory(memoForm.getCategory());
    memo.setBookName(memoForm.getBookName());
    final Account account = new Account();
    account.setId(customSecurityAccount.getId());
    account.setAccountName(customSecurityAccount.getAccountName());
    account.setEmail(customSecurityAccount.getEmail());
    account.setPassword(customSecurityAccount.getPassword());
    account.setRoles(customSecurityAccount.getRoles());
    account.setMemos(customSecurityAccount.getMemos());
    memo.setAccount(account);
    return memo;
  }

  // Checking if the user is an admin.
  private boolean isAdmin(CustomSecurityAccount customSecurityAccount) {
    final String string = customSecurityAccount.getAuthorities().toString();
    if (string.contains("ROLE_ADMIN")) return true;
    return false;
  }
}
