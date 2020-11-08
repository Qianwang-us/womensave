package com.techtogether.womensave.model;

import java.util.Objects;

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
	
	@Column(name="month_saved_amount")
	private double monthSavedAmount;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	public SaveRecord() {}

	public SaveRecord(int year, int month, double monthSavedAmount, User user) {
		super();
		this.year = year;
		this.month = month;
		this.monthSavedAmount = monthSavedAmount;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getMonthSavedAmount() {
		return monthSavedAmount;
	}

	public void setMonthSavedAmount(double monthSavedAmount) {
		this.monthSavedAmount = monthSavedAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaveRecord other = (SaveRecord) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
