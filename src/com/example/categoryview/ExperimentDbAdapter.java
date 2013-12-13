package com.example.categoryview;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class ExperimentDbAdapter extends DBAdapter {
	
	//New Experiment ID
	static long newExpId;
	// Experiments Table
	private static final String DATABASE_EXPERIMENTS_TABLE = "Experiments";

	// Columns Names
	private static final String EXP_ID = "expId";
	private static final String EXP_NAME = "expName";
	private static final String EXP_CATID = "catId";
	// Added one row
	private static final String EXP_DESC = "expDesc";
	private static final String EXP_NO_OF_STEPS = "noOfSteps";
	private static final String EXP_CREATEDBY = "createdBy";
	private static final String EXP_CREATEDON = "createdOn";
	private static final String EXP_UPDATEDBY = "updatedBy";
	private static final String EXP_UPDATEDON = "updatedOn";

	public ExperimentDbAdapter(Context ctx) {
		super(ctx);
	}

	//Method to add data into the experiments table
	public void addExperiment(Experiment exp)
	{
	ContentValues values = new ContentValues();
	values.put(EXP_NAME, exp.getExpName());
	values.put(EXP_DESC, exp.getExpDesc());
	values.put(EXP_CATID, exp.getCatId());
	values.put(EXP_NO_OF_STEPS, exp.getNoOfSteps());
	newExpId=db.insert(DATABASE_EXPERIMENTS_TABLE,  null, values);
	} 
	public Cursor getCategory()
	{
	 return db.query(DATABASE_EXPERIMENTS_TABLE, new String[]{EXP_ID,EXP_NAME,EXP_DESC,EXP_NO_OF_STEPS, 
			 												 EXP_CREATEDBY,EXP_CREATEDON,EXP_UPDATEDBY, 
			 												 EXP_UPDATEDON},null,null,null,null,null);
	}
	
	
	// Method to read data from experiments table where catId=catId.
	public List<Experiment> readExperiment(int catId) {
		List<Experiment> expList = new ArrayList<Experiment>();
		// Select All Query
		//catId++;
		String selectQuery = "SELECT * FROM Experiments where catId=" + catId;
				//+ " ORDER BY catName ";
		
		//Trial by amit
		//String sqlQuery="update Experiments set noOfSteps=16 where expId=1";
		//Cursor c1=db.rawQuery(sqlQuery,null);
		
		Cursor c = db.rawQuery(selectQuery, null);
		// looping through all rows and adding to list

		if (c.moveToFirst()) {
			do {
				Experiment newExp = new Experiment(c.getString(1),c.getString(2),c.getInt(3),c.getInt(4));
				newExp.setExpId(c.getInt(0));
				// Adding contact to list
				expList.add(newExp);
			} while (c.moveToNext());
		}
		// close inserting data from database
		db.close();

		// return contact list
		return expList;
	}
	
	public boolean deleteExperiment(int deleteExpId)
	{
		db.execSQL("delete from Experiments where expId="+ deleteExpId);
		return true;
	}
}

