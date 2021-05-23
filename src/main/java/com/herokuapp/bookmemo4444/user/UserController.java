package com.herokuapp.bookmemo4444.user;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.service.UserService;

@Controller
public class UserController {
	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String getTopPage() {
		return "top-page";
	}

	@GetMapping("/login")
	public String getLoginPage(LoginForm loginForm, Model model) {

		return "user/login";
	}

	@PostMapping("/login")
	public String postLogin(@Validated LoginForm loginForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		User user = userService.findByEmailAndPass(loginForm.getEmail(), loginForm.getPassword());
		if (result.hasErrors() || user == null) {
			return "user/login";
		}
		redirectAttributes.addFlashAttribute("userId", user.getUserId());
		user.setRememberUser(session.getId());
		return "redirect:/memo/";
	}

	@GetMapping("/signup")
	public String getSignupPage(SignupForm signupForm, Model model) {
		return "user/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@Validated SignupForm signupForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		User tmpUser = makeUser(signupForm, 0, session);
		if (result.hasErrors() || tmpUser == null) {
			model.addAttribute("signupForm", signupForm);
			return "user/signup";
		}
		tmpUser.setRememberUser(session.getId());
		if (!userService.insert(tmpUser)) {
			model.addAttribute("error", "メールアドレスは既に登録されています");
			return "user/signup";
		}
		User user = userService.findByEmailAndPass(tmpUser.getUserEmail(), tmpUser.getUserPassword());
		redirectAttributes.addFlashAttribute("userId", user.getUserId());
		return "redirect:/memo/";

	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/profile/{userId}")
	public String getProfile(@PathVariable("userId") int userId, SignupForm signupForm, Model model) {
		User user = userService.findById(userId);
		signupForm.setUserId(String.valueOf(userId));
		signupForm.setUserName(user.getUserName());
		signupForm.setEmail(user.getUserEmail());
		signupForm.setPassword(user.getUserPassword());
		model.addAttribute("signupForm", signupForm);
		return "user/user-profile";
	}

	@PostMapping("/profile/update")
	public String postProfile(@Validated SignupForm signupForm, BindingResult result, HttpSession session, Model model,
			RedirectAttributes redirectAttributes) {
		int userId = Integer.parseInt(signupForm.getUserId());
		User user = makeUser(signupForm, userId, session);
		if (result.hasErrors() || user == null) {
			model.addAttribute("signupForm", signupForm);
			return "user/user-profile";
		} else {
			userService.update(user);
			redirectAttributes.addFlashAttribute("userId", userId);
			return "redirect:/memo/";
		}
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@PathVariable String userId, @Validated SignupForm signupForm, BindingResult result,
			Model model) {
		userService.delete(Integer.parseInt(userId));
		return "redirect:/";
	}

	@GetMapping("/test")
	public String test(SignupForm signupForm, Model model) {
		List<User> userList = userService.getAll();
		model.addAttribute("userList", userList);
		return "user/test";
	}

	private User makeUser(SignupForm signupForm, int userId, HttpSession session) {
		User user = new User();
		if (userId != 0) {
			user.setUserId(userId);
		}
		user.setUserName(signupForm.getUserName());
		user.setUserEmail(signupForm.getEmail());
		user.setUserPassword(signupForm.getPassword());
		user.setRememberUser(session.getId());
		return user;
	}
}
