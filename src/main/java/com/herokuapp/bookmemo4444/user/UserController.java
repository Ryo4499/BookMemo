package com.herokuapp.bookmemo4444.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.herokuapp.bookmemo4444.entity.User;
import com.herokuapp.bookmemo4444.service.UserService;

@Controller
public class UserController {
	private final UserService userService;
	private final HttpSession session;

	@Autowired
	public UserController(UserService userService, HttpSession session) {
		this.userService = userService;
		this.session = session;
	}

	@GetMapping("/")
	public String getTopPage() {
		//TODO Change toppage
		return "top-page";
	}

	@GetMapping("/login")
	public String getLoginPage(LoginForm loginForm, Model model) {
		return "user/login";
	}

	@GetMapping("/signup")
	public String getSignupPage(SignupForm signupForm, Model model) {
		return "user/signup";
	}

	@PostMapping("/signup")
	public String postSignup(@Validated SignupForm signupForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		User tmpUser = makeUser(signupForm, (long) 0, session.getId());
		//TODO INS USER
		return "redirect:/memo/";

	}

	@GetMapping("/logout")
	public String getLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@GetMapping("/profile")
	public String getProfile(SignupForm signupForm, Model model) {
		String userId = session.getAttribute("userId").toString();
		//TODO GET USER DATA
		return "user/user-profile";
	}

	@PostMapping("/profile/update")
	public String postProfile(@Validated SignupForm signupForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		Long userId = Long.parseLong(session.getAttribute("userId").toString());
		User user = makeUser(signupForm, userId, session.getId());
		if (result.hasErrors() || user == null) {
			model.addAttribute("signupForm", signupForm);
			return "user/user-profile";
		} else {
		//TODO USER UPDATE
			return "redirect:/memo/";
		}
	}

	@GetMapping("/delete")
	public String deleteUser(@Validated SignupForm signupForm, BindingResult result, Model model) {
		//TODO USER delete
		return "redirect:/";
	}

	private User makeUser(SignupForm signupForm, Long userId, String sessionId) {
		User user = new User();
		if (userId != 0) {
			user.setUserId(userId);
		}
		user.setUserName(signupForm.getUserName());
		user.setUserEmail(signupForm.getEmail());
		user.setUserPassword(signupForm.getPassword());
		return user;
	}
}
