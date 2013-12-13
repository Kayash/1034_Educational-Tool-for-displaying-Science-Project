package com.example.categoryview;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

public class StepDbAdapter extends DBAdapter {
	

	public StepDbAdapter(Context ctx) {
		super(ctx);
		// TODO Auto-generated constructor stub
	}



	//Experiments Table
	private static final String DATABASE_STEPS_TABLE = "Steps";
	
	//Columns Names
	private static final String STEP_NO = "stepNo";
	private static final String STEP_EXP_ID = "expId";
	private static final String STEP_DESC = "stepDesc"; 
	private static final String STEP_IMAGE = "imageLink"; 
	private static final String STEP_PDF      =  "pdfLink";
	private static final String STEP_ANIMATION  = "animationLink";
	private static final String STEP_CREATEDBY = "createdBy";
	private static final String STEP_CREATEDON = "createdOn";
	private static final String STEP_UPDATEDBY  = "updatedBy";
	private static final String STEP_UPDATEDON  = "updatedOn" ;
	
	
	
	 
	
	public void addStep(Step stp){
		
		ContentValues values = new ContentValues();
		
		values.put(STEP_DESC,  stp.getStepDesc()); 
		values.put(STEP_IMAGE, stp.getImageLink());
		values.put(STEP_PDF, stp.getPdfLink());
		values.put(STEP_ANIMATION, stp.getAnimationLink());
		values.put(STEP_EXP_ID, ExperimentDbAdapter.newExpId);
		
		//adding record to steps table
		long stepId;
		stepId=db.insert(DATABASE_STEPS_TABLE,  null, values);
		
		//creating step folder
		/*String path = Environment.getExternalStorageDirectory().toString()+"/Chhote Scientists/Category_1/Experiment_1";
		File directory = new File (path+"/Step_"+String.valueOf(stepId));
		directory.mkdir();*/
		 
	}

	 
	
	public List<Step> readStep(int expClickedId)
	{
			List<Step> stepList = new ArrayList<Step>();	
			//Cursor c = db.rawQuery("select * from Steps where expId="+expClickedId,null);
			Cursor c = db.rawQuery("select * from Steps",null);// where expId=?" ,new String [] {String.valueOf(expClickedId)});
			// looping through all rows and adding to list 
			if (c.moveToFirst()) {
			do { 
     			Step stp = null;
 			    stp = new Step(c.getString(2),c.getString(3),c.getString(4),c.getString(5));
 			    stepList.add(stp);
			} while (c.moveToNext());
			}
		 
			return stepList;
		
	}
	
	public boolean deleteStep(int deleteId)
	{
		db.execSQL("delete from Steps where expId="+deleteId);
		return true;
	}
}
	
