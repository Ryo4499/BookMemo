package xyz.bookmemo.controller;

import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xyz.bookmemo.entity.Account;
import xyz.bookmemo.entity.Role;
import xyz.bookmemo.form.SignupForm;
import xyz.bookmemo.form.UpdateForm;
import xyz.bookmemo.repository.AccountRepository;
import xyz.bookmemo.repository.MemoRepository;
import xyz.bookmemo.repository.RoleRepository;
import xyz.bookmemo.security.CustomSecurityAccount;

/** The class is a controller class that handles the user's account information */
@Controller
public class AccountController {

  private final AccountRepository accountRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  private final HttpServletRequest req;

  private final MemoRepository memoRepository;

  @Autowired
  public AccountController(
      AccountRepository accountRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder,
      HttpServletRequest req,
      MemoRepository memoRepository) {
    this.accountRepository = accountRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
    this.memoRepository = memoRepository;
    this.req = req;
  }

  /**
   * This function returns the top-page.html file
   *
   * @return The top-page.html file
   */
  @GetMapping("/")
  public String getTopPage() {
    return "top-page";
  }

  /**
   * It returns the login page
   *
   * @return The login page.
   */
  @GetMapping("/login")
  public String getLoginPage() {
    return "account/login";
  }

  /**
   * The function takes a Model object as a parameter, adds a SignupForm object to the model, and
   * returns the name of the view to be rendered
   *
   * @param model The model is a Map that is used to store the data that will be displayed on the
   *     view page.
   * @return A string that is the name of the view.
   */
  @GetMapping("/signup")
  public String getSignupPage(Model model) {
    model.addAttribute("signupForm", new SignupForm());
    return "account/signup";
  }

  /**
   * Do signup process. If the form has errors, reset the password and return the form.
   *
   * @param signupForm The object that contains the input values of the form.
   * @param result The result of the validation.
   * @param model Model
   * @return The signup form is being returned.
   */
  @PostMapping("/signup")
  public String postSignup(@Validated SignupForm signupForm, BindingResult result, Model model) {
    if (result.hasErrors()) {
      signupForm.resetPassword();
      model.addAttribute("signupForm", signupForm);
      return "account/signup";
    }

    signupForm.setPassword(passwordEncoder.encode(signupForm.getPassword()));

    if (!passwordEncoder.matches(signupForm.getRePassword(), signupForm.getPassword())) {
      signupForm.resetPassword();
      model.addAttribute("errorPass", "パスワードが一致しません");
      model.addAttribute("signupForm", signupForm);
      return "account/signup";
    }

    final Account account = makeAccountSignUpForm(signupForm);

    accountRepository.save(account);

    return "redirect:/signup-success";
  }

  /**
   * It returns the signup success page
   *
   * @return The signup success page.
   */
  @GetMapping("/signup-success")
  public String getSignupSuccess() {
    return "account/signup-success";
  }

  /**
   * It returns the thanks page
   *
   * @return The thanks page.
   */
  @GetMapping("/thanks")
  public String getThanksPage() {
    return "account/thanks";
  }

  /**
   * It returns the profile page
   *
   * @return The profile page.
   */
  @GetMapping("/profile")
  public String getProfile(
      @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
    final UpdateForm updateForm = new UpdateForm();
    updateForm.setAccountName(customSecurityAccount.getAccountName());
    updateForm.setEmail(customSecurityAccount.getEmail());
    model.addAttribute("updateForm", updateForm);
    return "account/profile";
  }

  /**
   * It returns the profile page
   *
   * @return The profile page.
   */
  @PostMapping("/profile")
  public String postProfile(
      @Validated UpdateForm updateForm,
      BindingResult result,
      @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount,
      Model model,
      RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      updateForm.resetPassword();
      model.addAttribute("updateForm", updateForm);
      return "account/profile";
    }

    updateForm.setNewPassword(passwordEncoder.encode(updateForm.getNewPassword()));
    if (!passwordEncoder.matches(updateForm.getOldPassword(), customSecurityAccount.getPassword())
        || !passwordEncoder.matches(updateForm.getRePassword(), updateForm.getNewPassword())) {
      updateForm.resetPassword();
      model.addAttribute("errorPass", "パスワードが一致しません");
      model.addAttribute("updateForm", updateForm);
      return "account/profile";
    }
    final Account account = makeAccountUpdateForm(updateForm);
    account.setMemos(customSecurityAccount.getMemos());
    account.setRoles(customSecurityAccount.getRoles());
    accountRepository.updateAccount(
        customSecurityAccount.getId(),
        account.getAccountName(),
        account.getEmail(),
        account.getPassword());
    redirectAttributes.addFlashAttribute("success", "更新が完了しました");
    return "redirect:/profile";
  }

  @GetMapping("/delete")
  public String getDeletePage(
      @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount, Model model) {
    model.addAttribute("accountName", customSecurityAccount.getAccountName());
    model.addAttribute("email", customSecurityAccount.getEmail());
    return "account/account-delete-confirm";
  }

  @PostMapping("/delete")
  public String postdeleteAccount(
      @AuthenticationPrincipal CustomSecurityAccount customSecurityAccount) {
    final Account account = new Account();
    account.setId(customSecurityAccount.getId());
    account.setEmail(customSecurityAccount.getEmail());
    account.setAccountName(customSecurityAccount.getAccountName());
    account.setPassword(customSecurityAccount.getPassword());
    req.getSession().invalidate();
    memoRepository.deleteByAccount(account.getId());
    accountRepository.delete(account);
    return "redirect:/thanks";
  }

  // A method that creates an Account object from the SignupForm object.
  private Account makeAccountSignUpForm(SignupForm signupForm) {
    final Account account = new Account();
    account.setAccountName(signupForm.getAccountName());
    account.setEmail(signupForm.getEmail());
    account.setPassword(signupForm.getPassword());
    final Role role = roleRepository.findByAuthority("ROLE_USER");
    final Set<Role> roles = new HashSet<>();
    roles.add(role);
    account.setRoles(roles);
    return account;
  }

  // A method that creates an Account object from the UpdateForm object.
  private Account makeAccountUpdateForm(UpdateForm updateForm) {
    final Account account = new Account();
    account.setAccountName(updateForm.getAccountName());
    account.setEmail(updateForm.getEmail());
    account.setPassword(updateForm.getNewPassword());
    return account;
  }
}
