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
@Table(name="goals")
public class Goal {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="year")
	private int year;
	
	@Column(name="year_goal")
	private double yearGoal;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable=false)
	private User user;
	
	public Goal() {}

	public Goal(int year, double yearGoal, User user) {
		super();
		this.year = year;
		this.yearGoal = yearGoal;
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

	public double getYearGoal() {
		return yearGoal;
	}

	public void setYearGoal(double yearGoal) {
		this.yearGoal = yearGoal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Goal [id=" + id + ", year=" + year + ", yearGoal=" + yearGoal + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goal other = (Goal) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
