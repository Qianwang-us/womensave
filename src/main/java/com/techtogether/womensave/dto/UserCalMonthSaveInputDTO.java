package com.techtogether.womensave.dto;

public class UserCalMonthSaveInputDTO {

	private double annualIncome;
	private int currentAge;
	private int expectedRetireAge;
	
	public UserCalMonthSaveInputDTO() {}
	
	public UserCalMonthSaveInputDTO(double annualIncome, int currentAge, int expectedRetireAge) {
		super();
		this.annualIncome = annualIncome;
		this.currentAge = currentAge;
		this.expectedRetireAge = expectedRetireAge;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}
	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}
	public int getCurrentAge() {
		return currentAge;
	}
	public void setCurrentAge(int currentAge) {
		this.currentAge = currentAge;
	}
	public int getExpectedRetireAge() {
		return expectedRetireAge;
	}
	public void setExpectedRetireAge(int expectedRetireAge) {
		this.expectedRetireAge = expectedRetireAge;
	}
	
	
}
