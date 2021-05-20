package com.herokuapp.bookmemo4444.user;

import java.util.List;
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

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String getLoginPage(LoginForm loginForm, Model model) {
		return "user/login";
	}

	@GetMapping("/signup")
	public String getSignupPage(SignupForm signupForm, Model model) {
		return "user/signup";
	}

	@PostMapping("/login")
	public String doLogin(@Validated LoginForm loginForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Login");
			return "user/login";
		}
		redirectAttributes.addFlashAttribute("complete", "Login!!!");
		return "redirect:/memo/memo-list";
	}

	@PostMapping("/signup")
	public String doSignup(@Validated SignupForm signupForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes,HttpSession session) {
		if (result.hasErrors()) {
			model.addAttribute("title", "Signup");
			return "user/signup";
		}
		
		User user = new User();
		user.setUserName(signupForm.getUserName());
		user.setUserEmail(signupForm.getEmail());
		user.setUserPassword(signupForm.getPassword());
		user.setRememberUser(session.getId());
		
		userService.save(user);
		redirectAttributes.addFlashAttribute("complete", "Regester!");
		return "redirect:/memo/memo-list";
	}
}
