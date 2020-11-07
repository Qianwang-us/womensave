package com.techtogether.womensave.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {

	@Id
	private Integer id;
	private String name;
	private String email;
	private String password;

}
