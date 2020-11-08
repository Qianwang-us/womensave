package com.techtogether.womensave.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techtogether.womensave.dto.MonthRecordDTO;
import com.techtogether.womensave.dto.UserCalMonthSaveInputDTO;
import com.techtogether.womensave.model.Goal;
import com.techtogether.womensave.model.SaveRecord;
import com.techtogether.womensave.model.User;
import com.techtogether.womensave.repository.GoalRepository;
import com.techtogether.womensave.repository.SaveRecordRepository;
import com.techtogether.womensave.util.DashBoardInfoUtil;
import com.techtogether.womensave.util.GoalCalUtil;

@Controller
public class HomeController {
	
	@Autowired
	GoalRepository goalRepository;
	
	@Autowired
	SaveRecordRepository saveRecordRepository;

	@GetMapping("/")
	public String showHomePage() {
		return "index";
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
	
	//after user login, see his/her dashboard
	//show year goal and month goal if already saved
		@GetMapping("/dashboard")
		public String showDashboard(HttpSession session, Model model) {
			UserCalMonthSaveInputDTO input = new UserCalMonthSaveInputDTO();
			//fill with user input
			model.addAttribute("input", input);
			
			User currentUser = (User)session.getAttribute("currentUser");
			String username = currentUser.getName();
			
			//fill with user name
			model.addAttribute("name", username);
			
			int year = LocalDate.now().getYear();
			model.addAttribute("year", year);
			
			Optional<Goal> goalOption = goalRepository.findByYearAndUser(year, currentUser);
			
			if(goalOption.isEmpty()) {
				model.addAttribute("noRecord", true);
				return "dashboard";
			}
			
			Goal goal = goalOption.get();
			double yearGoal = goal.getYearGoal();
			
			//fill with year goal
			model.addAttribute("yearGoal", yearGoal);
			
			List<SaveRecord> saveRecordList = saveRecordRepository.findByYearAndUser(year, currentUser);
			DashBoardInfoUtil dashboard = new DashBoardInfoUtil(yearGoal/12, saveRecordList);
			
			List<MonthRecordDTO> records = dashboard.getMonthRecords();
			
			model.addAttribute("records", records);
			
			System.out.println("showDashboardWithCal: records: "+records);
			
			return "dashboard";
		}
		
		//after user login and click "calculate" button on dashboard page
		@PostMapping("/dashboard")
		public String showDashboardWithCal(@ModelAttribute("input") UserCalMonthSaveInputDTO input, HttpSession session, Model model) {
			//fill with user input
			model.addAttribute("input", input);
			
			User currentUser = (User)session.getAttribute("currentUser");
			String username = currentUser.getName();
			
			//fill with user name
			model.addAttribute("name", username);
			
			//fill with calculate result
			double P = GoalCalUtil.calMonthSave(input.getAnnualIncome(), input.getCurrentAge(), input.getExpectedRetireAge());
			
			int year = LocalDate.now().getYear();
			model.addAttribute("year", year);
			
			Optional<Goal> goalOption = goalRepository.findByYearAndUser(year, currentUser);
			Goal goal;
			double yearGoal = ((double)Math.round(P*12*100))/100;
			model.addAttribute("yearGoal", yearGoal);
			
			if(goalOption.isEmpty()) {
				goal = new Goal(year, yearGoal, currentUser);
				goalRepository.save(goal);
			}else {
				goal = goalOption.get();
				goal.setYearGoal(yearGoal);
				goalRepository.save(goal);
			}
			
			model.addAttribute("yearGoal", yearGoal);
			
			List<SaveRecord> saveRecordList = saveRecordRepository.findByYearAndUser(year, currentUser);
			
			DashBoardInfoUtil dashboard = new DashBoardInfoUtil(((double)Math.round(P*100))/100, saveRecordList);
			
			List<MonthRecordDTO> records = dashboard.getMonthRecords();
			
			model.addAttribute("records", records);
			
			System.out.println("showDashboardWithCal: records: "+records);
			return "dashboard";
		}
		
		//from track button in the dashboard
		@GetMapping("/track/view")
		public String trackView(@ModelAttribute("month_record") MonthRecordDTO monthRecord, HttpSession session, Model model) {
			model.addAttribute("record", monthRecord);
			
			return "track";
		}
		
		@GetMapping("/track/add")
		public String trackAdd(@ModelAttribute("month_record") MonthRecordDTO monthRecord, HttpSession session, @RequestParam double addAmount, Model model) {
			User currentUser = (User)session.getAttribute("currentUser");
			Optional<SaveRecord> saveRecord= saveRecordRepository.findByYearAndMonthAndUser(LocalDate.now().getYear(), monthRecord.getMonthIndex(), currentUser);
			SaveRecord oneRecord;		
			if(saveRecord.isEmpty()) {
				oneRecord = new SaveRecord(LocalDate.now().getYear(), monthRecord.getMonthIndex(), addAmount, currentUser);
			}else {
				oneRecord = saveRecord.get();
				oneRecord.setMonthSavedAmount(monthRecord.getMonthSavedMoney()+addAmount);
				saveRecordRepository.save(oneRecord);
			}
			//new SaveRecord(LocalDate.now().getYear(), monthRecord.getMonthIndex(), monthRecord.getMonthSavedMoney()+addAmount, currentUser);
			return "redirect:/dashboard";
		}
}
