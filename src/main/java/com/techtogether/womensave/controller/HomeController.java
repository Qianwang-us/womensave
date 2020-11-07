package com.techtogether.womensave.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.techtogether.womensave.dto.UserCalMonthSaveInputDTO;
import com.techtogether.womensave.util.GoalCalUtil;

@Controller
public class HomeController {

	@GetMapping("/")
	public String showHomePage() {
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String showDashboard(HttpSession session, Model model) {
		return "dashboard";
	}
	
	//user does not login yet, but use calculate function to get report
	// return goal report with each month save for this year
	@PostMapping("/cal_month_save") 
	public String calMonthSave(@ModelAttribute("user_input") UserCalMonthSaveInputDTO input, Model model) {
		double P = GoalCalUtil.calMonthSave(input.getAnnualIncome(), input.getCurrentAge(), input.getExpectedRetireAge());
		
		int year = LocalDate.now().getYear();
		
		model.addAttribute("month_save", P);
		model.addAttribute("year", year);
		return "goal_report";
	}
}
