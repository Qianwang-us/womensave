package com.techtogether.womensave.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techtogether.womensave.dto.UserCalMonthSaveInputDTO;
import com.techtogether.womensave.util.GoalCalUtil;

@RestController
public class CalMonthSaveRestController {

	@PostMapping("/cal_month_save") 
	public double calMonthSave(@ModelAttribute("user_input") UserCalMonthSaveInputDTO input) {
		double P = GoalCalUtil.calMonthSave(input.getAnnualIncome(), input.getCurrentAge(), input.getExpectedRetireAge());
		
		return P;
	}
}
