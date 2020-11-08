package com.techtogether.womensave.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.techtogether.womensave.dto.MonthRecordDTO;
import com.techtogether.womensave.model.SaveRecord;

public class DashBoardInfoUtil {
	private double monthGoal;
	private List<SaveRecord> monthSavedRecords;
	
	public DashBoardInfoUtil(double monthGoal, List<SaveRecord> monthSavedRecords) {
		this.monthGoal = monthGoal;
		this.monthSavedRecords = monthSavedRecords;
	}

	public List<MonthRecordDTO>  getMonthRecords(){
		String[] monthNameList = {"Jan", "Feb", "Mar", "Apr", "May", 
				"Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
		List<MonthRecordDTO> monthRecords = new ArrayList<>();
		Double[] monthSavedArray = getMonthSavedAmout();
		for(int i = 0; i<12; i++) {
			boolean isAchieved = (monthGoal <= monthSavedArray[i]);
			MonthRecordDTO monthRecord = new MonthRecordDTO(i+1, monthNameList[i], monthGoal, monthSavedArray[i], isAchieved);
			monthRecords.add(monthRecord);
		}
		
		return monthRecords;
	}
	
	private Double[] getMonthSavedAmout() {
		Double[] monthSavedArray = new Double[12];
		Arrays.fill(monthSavedArray, 0);
		if(monthSavedRecords == null || monthSavedRecords.size()==0) {
			return monthSavedArray;
		}
		
		//assume saved month starts from 1
		monthSavedRecords.forEach(record -> monthSavedArray[record.getMonth()-1]=record.getMonthSavedAmount());
		return monthSavedArray;
	}
	
}
