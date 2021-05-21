package com.herokuapp.bookmemo4444.user;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping("/test")
	public String test(SignupForm signupForm, Model model) {
		List<User> userList = userService.getAll();
		model.addAttribute("userList", userList);
		return "user/test";
	}

	@GetMapping("/login")
	public String getLoginPage(LoginForm loginForm, Model model) {

		return "user/login";
	}

	@GetMapping("/signup")
	public String getSignupPage(SignupForm signupForm, Model model) {
		return "user/signup";
	}

	@GetMapping("/{id}")
	public String showUserUpdate(@PathVariable("userId") int userId, SignupForm signupForm, @PathVariable int id,
			Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("userId", id);
		model.addAttribute("title", "更新用フォーム");
		return "user/user-profile";
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@RequestParam("userId")int id, Model model) {
		System.out.println(id);
		userService.delete(id);
		return "redirect:/";
	}

	@PostMapping("/login")
	public String doLogin(@Validated LoginForm loginForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Login");
			return "user/login";
		}
		redirectAttributes.addFlashAttribute("session_id", session.getId());
		return "redirect:/memo/memo-list";
	}

	@PostMapping("/signup")
	public String doSignup(@Validated SignupForm signupForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpSession session) {

		User user = makeUser(signupForm, 0, session);

		if (!result.hasErrors()) {
			userService.insert(user);
			redirectAttributes.addFlashAttribute("userId", user.getUserId());
			return "redirect:/memo/memo-list";
		} else {
			model.addAttribute("signupForm", signupForm);
			List<User> userList = userService.getAll();
			model.addAttribute("userList", userList);
			return "signup";
		}

	}

	@PostMapping("/update")
	public String update(@Validated @ModelAttribute SignupForm signupForm, BindingResult result, HttpSession session,
			@RequestParam("userId") int userId, Model model, RedirectAttributes redirectAttributes) {
		User user = makeUser(signupForm, userId, session);
		if (!result.hasErrors()) {
			userService.update(user);
			return "redirect:/memo/memo-list";
		} else {
			model.addAttribute("signupForm", signupForm);
			return "user/user-profile";
		}
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
