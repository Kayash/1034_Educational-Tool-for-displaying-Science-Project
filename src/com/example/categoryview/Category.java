package com.example.categoryview;

import java.sql.Date;

// This is an category class which set and get the data for each fields...
public class Category {

	private int catId;
	private String catName;
	private String catDesc;
	private Integer catImage;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	
	public Category()
	{}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public Category(int catId, String catName, String catDesc,int catImage) {
		this.catId = catId;
		this.catName = catName;
		this.catDesc = catDesc;
		this.catImage = catImage;
	}

	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}
	public String getCatName() {
		return catName;
	}

	public String getCatDesc() {
		return catDesc;
	}

	public Integer getCatImage() {
		return catImage;
	}

	public int getCatId() {
		return catId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

}
