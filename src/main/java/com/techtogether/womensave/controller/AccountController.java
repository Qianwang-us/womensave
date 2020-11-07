package com.techtogether.womensave.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.techtogether.womensave.model.User;
import com.techtogether.womensave.repository.UserRepository;

@Controller
public class AccountController {
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/login")
	public String login(Model model) {
		User user= new User();
		model.addAttribute("user", user);		
		return "login";
	}
	
	@GetMapping("/login-auth")
	public String loginAuthenticate(@ModelAttribute("user") User user, Model model, HttpSession session) {
		Optional<User> retrievedUser = userRepository.findUserByEmail(user.getEmail());
		String errorMessage = "Invalid email or password, please try again";
		if(retrievedUser.isEmpty()) {
			model.addAttribute("message", errorMessage);
			return "error";
		}
		boolean valid = retrievedUser.get().getPassword().equals(user.getPassword());
		
		if(!valid) {
			model.addAttribute("message", errorMessage);
			return "error";
		}
		
		//after user successfully login, forwards to user dashboard
		session.setAttribute("currentUser", retrievedUser);
		
		return "dashboard";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register/save")
	public String registerSave(@ModelAttribute("user") User user) {
		return "register";
	}
}
