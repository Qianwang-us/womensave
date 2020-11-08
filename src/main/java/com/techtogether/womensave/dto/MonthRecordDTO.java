package com.techtogether.womensave.dto;

import java.util.Date;

public class MonthRecordDTO {
	private int monthIndex;
	private String monthName;
	private double monthSaveGoal;
	private double monthSavedMoney;
	private boolean achieved=false;
	//private Date dueDate;
	
	public MonthRecordDTO() {
	}
	
	public MonthRecordDTO(int monthIndex, String monthName, double monthSaveGoal, double monthSavedMoney, boolean isAchieved) {
		super();
		this.monthIndex = monthIndex;
		this.monthName = monthName;
		this.monthSaveGoal = monthSaveGoal;
		this.monthSavedMoney = monthSavedMoney;
		this.achieved = isAchieved;
	}

	public String getMonthName() {
		return monthName;
	}

	public void setMonthName(String monthName) {
		this.monthName = monthName;
	}

	public double getMonthSaveGoal() {
		return monthSaveGoal;
	}

	public void setMonthSaveGoal(double monthSaveGoal) {
		this.monthSaveGoal = monthSaveGoal;
	}

	public double getMonthSavedMoney() {
		return monthSavedMoney;
	}

	public void setMonthSavedMoney(double monthSavedMoney) {
		this.monthSavedMoney = monthSavedMoney;
	}



	public boolean getAchieved() {
		return achieved;
	}

	public void setAchieved(boolean achieved) {
		this.achieved = achieved;
	}

	public int getMonthIndex() {
		return monthIndex;
	}

	public void setMonthIndex(int monthIndex) {
		this.monthIndex = monthIndex;
	}

	@Override
	public String toString() {
		return "MonthRecordDTO [monthIndex=" + monthIndex + ", monthName=" + monthName + ", monthSaveGoal="
				+ monthSaveGoal + ", monthSavedMoney=" + monthSavedMoney + ", isAchieved=" + achieved + "]";
	}

	


	
	
}
