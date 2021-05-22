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
		User user = makeUser(signupForm, 0, session);
		if (result.hasErrors() || user == null) {
			model.addAttribute("signupForm", signupForm);
			return "user/signup";
		} else {
			if (!userService.insert(user)) {
				model.addAttribute("error","メールアドレスは既に登録されています");
				return "user/signup";
			}
			redirectAttributes.addFlashAttribute("userId", user.getUserId());
			user.setRememberUser(session.getId());
			userService.update(user);
			return "redirect:/memo/";
		}
	}

	@GetMapping("/profile/{id}")
	public String getProfile(@PathVariable("userId") int userId, SignupForm signupForm, @PathVariable int id,
			Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		model.addAttribute("userId", id);
		return "user/user-profile";
	}

	@PostMapping("/profile/update")
	public String putProfile(@Validated @ModelAttribute SignupForm signupForm, BindingResult result,
			HttpSession session, @RequestParam("userId") int userId, Model model,
			RedirectAttributes redirectAttributes) {
		User user = makeUser(signupForm, userId, session);
		if (result.hasErrors() || user == null) {
			model.addAttribute("signupForm", signupForm);
			return "user/user-profile";
		} else {
			userService.update(user);
			return "redirect:/memo/memo-list";
		}
	}

	@GetMapping("/delete/{userId}")
	public String deleteUser(@RequestParam("userId") int id, Model model) {
		userService.delete(id);
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
