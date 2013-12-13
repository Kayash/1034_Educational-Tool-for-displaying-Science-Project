package com.example.categoryview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

public class UserDbAdapter extends DBAdapter {
	
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
			
			 
			public UserDbAdapter(Context ctx) {
				super(ctx);
		 
			}
		 
			
		//adding user record	
		 public void addUser(/*pass object*/){
			 ContentValues values = new ContentValues();
			 //values.put(USER_NAME, userobj.getUserName());
			 //values.put(USER_PASSWORD, userobj.getPassword());
			 values.put(USER_NAME, "Admin");
			 values.put(USER_PASSWORD, "123456");
			 db.insert(DATABASE_USERS_TABLE,  null, values);
		 }
		 
		 
		//reading user record
		public Cursor getUser()
		{
			return db.query(DATABASE_USERS_TABLE,new String[] {USER_ID,USER_NAME,USER_PASSWORD,USER_CREATEDBY,USER_CREATEDON,USER_UPDATEDBY,USER_UPDATEDON}, 
													null, null, null, null,null);
		}
		 
		//checking for valid  user name and password
		public boolean checkUser(User userobj)
		{
			Cursor c = getUser();
			if(c!=null){
				c.moveToFirst();
				do{
				if(userobj.getUserName().equalsIgnoreCase(c.getString(1))&& userobj.getPassword().equalsIgnoreCase(c.getString(2)))
				{
					return true;
				}
				}while(c.moveToNext());
			}
			return false;
		}
		 
		 
		 
			
			
			
	

}
