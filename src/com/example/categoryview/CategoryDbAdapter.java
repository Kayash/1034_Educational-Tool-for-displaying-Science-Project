package com.example.categoryview;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
public class CategoryDbAdapter extends DBAdapter {
	
	//CategoeiesTable  
 	private static final String DATABASE_CATEGORIES_TABLE = "Categories";	
	
	//Columns Names
    private static final String CAT_ID ="catId";
	private static final String CAT_NAME = "catName";
	private static final String CAT_DESC ="catDesc";
	private static final String CAT_IMAGE ="catImage";
	private static final String CAT_CREATEDBY = "createdBy";
	private static final String CAT_CREATEDON = "createdOn";
	private static final String CAT_UPDATEDBY = "updatedBy";
	private static final String CAT_UPDATEDON = "updatedOn";
	
	static int countNoOfCat;

	public CategoryDbAdapter(Context ctx) {
		super(ctx);
		 
	}
	
	public void addCategory(Category cat)
	{
	ContentValues values = new ContentValues();
	values.put(CAT_NAME, cat.getCatName());
	values.put(CAT_DESC, cat.getCatDesc());
	//values.put(CAT_IMAGE, R.drawable.ic_launcher);
	//values.put(CAT_CREATEDBY, "vikas");
    //values.put(KEY_CREATEDON, DATETIME('now'));
    //values.put(KEY_UPDATEDBY, value);
    //values.put(KEY_UPDATEDON, value);
	//adding record to categories table
	db.insert(DATABASE_CATEGORIES_TABLE,  null, values);
	} 
	 
	public Cursor getCategory()
	{
	 return db.query(DATABASE_CATEGORIES_TABLE, new String[]{CAT_ID,CAT_NAME,CAT_DESC,CAT_IMAGE , 
			 												 CAT_CREATEDBY,CAT_CREATEDON,CAT_UPDATEDBY, 
			 												 CAT_UPDATEDON},null,null,null,null,null);
	}
	
	public List<Category> readCategory()
	{
			List<Category> categoryList = new ArrayList<Category>();
			// Select All Query
			
			String selectQuery = "SELECT * FROM Categories ORDER BY catName ";
			 
			Cursor c = db.rawQuery(selectQuery, null);
			// looping through all rows and adding to list
			
			if (c.moveToFirst()) {
				int i=0;
			do {
				countNoOfCat++;
				i++;
				Category cat = null;
				switch(i)
				{
				case 1:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_1); break;
				case 2:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_2); break;
				case 3:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_3); break;
				case 4:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_4); break;
				case 5:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_5); break;
				case 6:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_6); break;
				case 7:cat = new Category(c.getInt(0),c.getString(1),c.getString(2),R.drawable.cat_7); i=0; break;
				}
			//Category cat = new Category(c.getString(1),c.getString(2),R.drawable.cat_4);	 
			// Adding contact to list
			categoryList.add(cat);
			} while (c.moveToNext());
			}
			// close inserting data from database
			db.close();
			
			// return contact list
			return categoryList;
		
	}
	public boolean deleteCategory(int catId)
	{
		db.execSQL("delete from Categories where catId="+catId);
		return false;
	}
	
	
	
}
 