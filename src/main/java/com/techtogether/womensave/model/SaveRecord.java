package com.techtogether.womensave.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="save_records")
public class SaveRecord {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="year")
	private int year;
	
	@Column(name="month")
	private int month;
	
	@Column(name="month_goal")
	private double monthGoal;
	
	@Column(name="month_saved_amount")
	private double monthSavedAmount;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
}
