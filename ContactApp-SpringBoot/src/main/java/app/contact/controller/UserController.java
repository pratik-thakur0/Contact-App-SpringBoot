package app.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.contact.command.UserCommand;
import app.contact.domain.User;
import app.contact.services.UserService;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(value = { "/", "/login" })
	public String loginForm() {
		return "login";
	}

	@PostMapping("/login-action")
	public String loginAction(@RequestParam String username, @RequestParam String password, Model m,
			HttpSession session) {
		User user = userService.login(username, password);
//		System.out.println(username);
//		System.out.println(password);
//		System.out.println(user.getStatus());
//		System.out.println(UserService.LOGIN_STATUS_ACTIVE);
//		System.out.println(user.getStatus() == UserService.LOGIN_STATUS_ACTIVE);
		if (user == null) {
			m.addAttribute("err", "Login Failed.");
			return "login";
		} else {
			if (user.getStatus() == UserService.LOGIN_STATUS_ACTIVE) {
				// Active

				session.setAttribute("userId", user.getUserId());
				session.setAttribute("role", user.getRole());
				session.setAttribute("user", user);

				if (user.getRole() == UserService.ROLE_USER) {
					return "redirect:/user/dashboard";
				} else if (user.getRole() == UserService.ROLE_ADMIN) {
					return "redirect:/admin/dashboard";
				} else {
					m.addAttribute("err", "Login Failed, Unexpected Error.");
					return "login";
				}
			} else {
				// Blocked
				m.addAttribute("err", "Login Failed, User is Blocked.");
				return "login";
			}
		}
	}

	@GetMapping("/register-form")
	public String registerForm(Model m) {
		m.addAttribute("ucmd", new UserCommand());
		return "reg_form";
	}

	@PostMapping("/register-action")
	public String registerAction(@ModelAttribute("ucmd") UserCommand cmd, Model m) {
		if (cmd.getTnc()) {
			try {
				User user = cmd.getUser();
				user.setRole(UserService.ROLE_USER);
				user.setStatus(UserService.LOGIN_STATUS_ACTIVE);
				userService.register(user);
				return "redirect:login?act=reg";
			} catch (Exception e) {
				m.addAttribute("err", "Something went wrong, or Try another username.");
				return "reg_form";
			}
		} else {
			// Denied
			m.addAttribute("err", "You are not agreed with terms and conditions.");
			return "reg_form";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // destroy user/admin session
		return "redirect:login?act=lo";
	}

	@GetMapping("/user/dashboard")
	public String userDashboard() {
		return "user_dashboard";
	}

	@GetMapping("/admin/dashboard")
	public String adminDashboard() {
		return "admin_dashboard";
	}

}
