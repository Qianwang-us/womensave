package com.techtogether.womensave.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techtogether.womensave.model.User;
import com.techtogether.womensave.repository.UserRepository;

@Controller
public class AccountController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/login")
	public String login() {
			
		return "login";
	}
	
	@PostMapping("/login/authenticate")
	public String loginAuthenticate(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		Optional<User> retrievedUser = userRepository.findUserByEmail(email);
		String errorMessage = "Invalid email or password, please try again";
		if(retrievedUser.isEmpty()) {
			model.addAttribute("message", errorMessage);
			return "error";
		}
		boolean valid = retrievedUser.get().getPassword().equals(password);
		
		if(!valid) {
			model.addAttribute("message", errorMessage);
			return "error";
		}
		
		//after user successfully login, forwards to user dashboard
		session.setAttribute("currentUser", retrievedUser.get());
		
		return "redirect:/dashboard";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		User user= new User();
		model.addAttribute("user", user);
		
		return "sign-up";
	}
	
	@PostMapping("/register/save")
	public String registerSave(@ModelAttribute("user") User user, Model model) {
		try {
			userRepository.save(user);
		}catch(Exception e) {
			model.addAttribute("message", "duplicated email, please try again");
			return "error";
		}
		
		model.addAttribute("message", "you've registered successfuly");
		model.addAttribute("user", new User());
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
