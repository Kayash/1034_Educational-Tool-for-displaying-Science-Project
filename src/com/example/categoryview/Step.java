package com.example.categoryview;

import java.sql.Date;

public class Step {
	 private int      stepNo;
	 private int      expId;
	 private String   stepDesc; 
	 private String   imageLink; 
	 private String   pdfLink;
	 private String   animationLink;
   	 private String   createdBy;
	 private Date     createdOn;
	 private String   updatedBy;
	 private Date     updatedOn;
	 
	 public Step(String stDesc , String stImage, String stPdf, String stVideo){
		 stepDesc = stDesc;
		 imageLink= stImage;
		 pdfLink = stPdf; 
		 animationLink=stVideo;
	 }
	 
	 public Step() {
		// TODO Auto-generated constructor stub
	}

	public String getAnimationLink() {
			return animationLink;
		}
	 public int getStepNo() {
		return stepNo;
	}

	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}

	public int getExpId() {
		return expId;
	}

	public void setExpId(int expId) {
		this.expId = expId;
	}

	public String getStepDesc() {
		return stepDesc;
	}

	public void setStepDesc(String stepDesc) {
		this.stepDesc = stepDesc;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}

	public String getPdfLink() {
		return pdfLink;
	}

	public void setPdfLink(String pdfLink) {
		this.pdfLink = pdfLink;
	}

	public void setAnimationLink(String animationLink2) {
		this.animationLink=animationLink2;
		
	}

	 
	 
	 
}
