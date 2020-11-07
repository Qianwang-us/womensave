package com.techtogether.womensave.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@Column(name="email", unique=true, nullable=false)
	private String email;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<Goal> yearGoals;
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	private List<SaveRecord> saveRecords;
	

}
