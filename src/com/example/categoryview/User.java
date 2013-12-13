package com.example.categoryview;

import java.sql.Date;

public class User {
	
		//data members
	    private int userId;
		private String userName;
		private String password;
		private String createdBy;
		private Date   createdOn;
		private String updatedBy;
		private Date   updatedOn;
	
	public User(String userName , String password)
	{
		this.userName = userName;
		this.password = password;
		//set date here
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public String getPassword()
	{
		return password;
	}
}
