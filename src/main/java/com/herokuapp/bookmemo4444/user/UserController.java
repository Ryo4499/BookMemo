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

	@GetMapping("/")
	public String getTopPage() {
		// TODO Change toppage
		return "top-page";
	}

//	@GetMapping("/login")
//	public String getLoginPage() {
//		return "user/login";
//	}
//
//	@GetMapping("/signup")
//	public String getSignupPage(SignupForm signupForm, Model model) {
//		return "user/signup";
//	}
//
//	@PostMapping("/signup")
//	public String postSignup(@Validated SignupForm signupForm, BindingResult result, Model model,
//			RedirectAttributes redirectAttributes) {
//
//		// TODO INS USER
//		return "redirect:/memo/";
//
//	}
//
//	@GetMapping("/profile")
//	public String getProfile(SignupForm signupForm, Model model) {
//
//		// TODO GET USER DATA
//		return "user/user-profile";
//	}
//
//	@PostMapping("/profile/update")
//	public String postProfile(@Validated SignupForm signupForm, BindingResult result, Model model,
//			RedirectAttributes redirectAttributes) {
//
//		return "user/user-profile";
//
//		// TODO USER UPDATE
//		// return "redirect:/memo/";
//
//	}
//
//	@GetMapping("/delete")
//	public String deleteUser(@Validated SignupForm signupForm, BindingResult result, Model model) {
//		// TODO USER delete
//		return "redirect:/";
//	}
//
//	private User makeUser(SignupForm signupForm, Long userId, String sessionId) {
//		User user = new User();
//		if (userId != 0) {
//			user.setUserId(userId);
//		}
//		user.setUserName(signupForm.getUserName());
//		user.setUserEmail(signupForm.getEmail());
//		user.setUserPassword(signupForm.getPassword());
//		return user;
//	}
}
