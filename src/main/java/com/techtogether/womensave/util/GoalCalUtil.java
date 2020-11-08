package com.techtogether.womensave.util;

/**
 * this class is used to calculate the money needed to save for each month with all the user input
 * @author qianwang
 *
 */
public class GoalCalUtil {
	private static final double r = 0.06;//annual return rate as a decimal (assume 6%)
	private static final int n = 12; //number of payment periods per year (12)
	private static final int lifeExpectancy = 85;

	public static double calMonthSave(double annualIncome, int currentAge, int expectedRetireAge) {
		double A;//total retirement saving balance
		int T = expectedRetireAge - currentAge;
		double P; //monthly payment they need to save
		
		double preRetireIncome;
		
		//Pre-retirement annual income =  Current income (1+ 0.03)^T 
		preRetireIncome = annualIncome * Math.pow(1+0.03, T);
		
		//A = Total balance needed for retirement  = .85(Pre-retirement income) * (100 - Retirement Age)
		A = 0.85 * preRetireIncome * (lifeExpectancy - expectedRetireAge);
		
		double diviend = A*(r/n);
		double divider = Math.pow((1+r/n), n*T) - 1;
		
		P = diviend/divider;
		//P = ((double)Math.round(diviend/divider*100))/100;
		
		return P;
	}
}
