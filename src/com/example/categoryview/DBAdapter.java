package com.example.categoryview;

//import java.sql.Date;
//import java.text.SimpleDateFormat;

//import android.content.ContentValues;
import android.content.Context;
//import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.text.format.DateFormat;
//import android.util.Log;
//import android.widget.Toast;

public class DBAdapter {
		
	
	//Users Table
	private static final String DATABASE_USERS_TABLE = "Users";
	
	//Columns Names
	private static final String USER_ID        = "userId";
	private static final String USER_NAME      = "userName";
	private static final String USER_PASSWORD  = "password";
	private static final String USER_CREATEDBY = "createdBy";
	private static final String USER_CREATEDON = "createdOn";
	private static final String USER_UPDATEDBY = "updatedBy";
	private static final String USER_UPDATEDON = "updatedOn";
	
		//create user table query
	   private static String USERS_TABLE ="create table Users( userId integer primary key autoincrement, "+
																	   "userName text , password text  ,"+
																	   "createdBy text , createdOn date,"+
																	   "updatedBy text , updatedOn date)";
		
		//create category table query
		private static String CATEGORIES_TABLE = "create table Categories (catId integer primary key autoincrement," +
														 				   "catName text not null ,catDesc text , createdBy text" +
														 				   "createdOn date, updatedBy text, updatedOn date)";
		
		//create experiments table query
		private static String EXPERIMENTS_TABLE = "create table Experiments (expId integer primary key autoincrement, " +
														 				     "expName text not null ,expDesc text, catId integer ,"+
														 				     "noOfSteps integer , createdBy text , "+
														 				     "createdOn date,  updatedBy text, "+
														 				     "updatedOn date , foreign key(catId) references categories(catId))";
		
		//create steps table query
		private static String STEPS_TABLE = "create table Steps (stepNo integer, " +
																 "expId integer," +
																 "stepDesc text , imageLink text,"+
																 "pdfLink text ,animationLink text, CreatedBy text,createdOn date,  updatedBy text, "+
																 "updatedOn date ,primary key(stepNo, expId), foreign key(expId) references experiments(expId))";

		
		
		//Experiments Table
		private static final String DATABASE_STEPS_TABLE = "Steps";
		
		//Columns Names
		private static final String STEP_NO = "stepNo";
		private static final String STEP_EXP_ID = "expId";
		private static final String STEP_DESC = "stepDesc"; 
		private static final String STEP_IMAGE = "imageLink"; 
		private static final String STEP_PDF      =  "pdfLink";
//		private static final String STEP_ANIMATION  = "animationLink";
		private static final String STEP_CREATEDBY = "createdBy";
		private static final String STEP_CREATEDON = "createdOn";
		private static final String STEP_UPDATEDBY  = "updatedBy";
		private static final String STEP_UPDATEDON  = "updatedOn" ;
		
		
		
				

		
		
	    //DataBase Version 
		private static final int DATABASE_VERSION = 1;
			
		//DataBase Name
		private static final String DATABASE_NAME = "APPDB.db";
	
	
		protected final Context context;
		protected static DatabaseHelper  DBHelper;
		protected static SQLiteDatabase db;
		
		
		public DBAdapter (Context ctx){
			this.context = ctx;
			DBHelper = new DatabaseHelper(context);
			}
		
	
		static class DatabaseHelper extends SQLiteOpenHelper
		{
			    DatabaseHelper(Context context){
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

			@Override
			public void onCreate(SQLiteDatabase db) {
				 try{
					 db.execSQL(USERS_TABLE);
					 db.execSQL(CATEGORIES_TABLE);
					 db.execSQL(EXPERIMENTS_TABLE);
					 db.execSQL(STEPS_TABLE);
				 }catch(SQLException e){
					 e.getStackTrace();
				 }
				
				 
			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				    db.execSQL("DROP TABLE IF EXISTS USERS");
					db.execSQL("DROP TABLE IF EXISTS CATEGORIES");
					db.execSQL("DROP TABLE IF EXISTS EXPERIMENTS");
					db.execSQL("DROP TABLE IF EXISTS STEPS");
					onCreate(db);
			}	
		}
		
		public DBAdapter open() throws SQLException{
			db =DBHelper.getWritableDatabase();
			return this;
		}
		
		public void  close(){
			DBHelper.close();
		}
		
}
 

