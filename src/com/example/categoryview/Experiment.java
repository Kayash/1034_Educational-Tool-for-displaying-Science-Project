package com.example.categoryview;


// This is an Experiment Class which set and get the data of each field..
public class Experiment {

	String expName;
	String expDesc;
	int catId,expId;
	int noOfSteps;

	public Experiment(String expName, String expDesc,int catId,int noOfSteps) {
		this.expName = expName;
		this.expDesc = expDesc;
		this.catId=catId;
		this.noOfSteps=noOfSteps;
	}
	
	public Experiment() {
		// TODO Auto-generated constructor stub
	}

	public void  setExpId(int expId)
	{
		this.expId=expId;
	}
	public int getNoOfSteps() {
		return noOfSteps;
	}
	public String getExpName() {
		return expName;
	}
	public int getCatId() {
		return catId;
	}
	public int getExpId()
	{
		return expId;
	}
	public String getExpDesc() {
		return expDesc;
	}

	public void setNoOfSteps(int no) {
	this.noOfSteps=no;
		
	}

	public void setExpName(String name) {
		this.expName=name;
		
	}

	public void setCatId(int id) {
		this.catId=id;
		
	}
	
}
