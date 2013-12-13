package com.example.categoryview;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	// Creating Objects and Variable
	List<Category> categoryList = new ArrayList<Category>();
	CategoryDbAdapter catdb = new CategoryDbAdapter(this);
	static int catClickedId;
	int catDeleteId;
	public static String languageToLoad = "en";
	
 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		
		Locale locale = new Locale(languageToLoad);
		Locale.setDefault(locale);
		Configuration config = new Configuration();
		config.locale = locale;
		getBaseContext().getResources().updateConfiguration(config, null);
		

		setContentView(R.layout.activity_main);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);
		
		// Creating Cat List View object
		View v=findViewById(R.id.categoryListView);
		registerForContextMenu(v);
		
		// Opening Database Connection & reading Data & Closing Connection.
		catdb.open();
		categoryList = catdb.readCategory();
		catdb.close();
		
		// populateCategoryList();
		populateListView();
		
		//Get Id on click listener...
		ListView list = (ListView) findViewById(R.id.categoryListView);
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				catDeleteId=categoryList.get(arg2).getCatId();
				return false;
			}
		});

		// Code for Setting Button...
		Button addExpButton = (Button) findViewById(R.id.settingButton);
		addExpButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
				Intent i = new Intent(MainActivity.this,ImportModule.class);
				startActivity(i);
				finish(); 
			}
		});	
			
		// Code for Home Button
		Button homeButton = (Button) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

			}
		});

		
		
		// Code for Close Button..
		Button closeButton = (Button) findViewById(R.id.closeButton);
		closeButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//Toast.makeText(MainActivity.this,""+CategoryDbAdapter.countNoOfCat, Toast.LENGTH_LONG).show();
	}
	
	/*
	 * private void populateCategoryList() { myCategory .add(new Category(
	 * "Amit Charkha",
	 * "Success is awarded to those who dream of endless possibilites who through determination"
	 * , R.drawable.exp)); myCategory .add(new Category( "Sanket Burse",
	 * "Do Not compare yourself with others if you compare then you are insulting YOURSELF"
	 * , R.drawable.exp)); myCategory .add(new Category( "Rohit Sarda",
	 * "No one manufacture lock without a key. Similarly, God wont give you a problem with a solution"
	 * , R.drawable.exp)); myCategory.add(new Category("Rakesh Bajaj",
	 * "Hamara Bajaj", R.drawable.exp)); myCategory.add(new
	 * Category("Vikas Datir", "It is an very difficult category to explain",
	 * R.drawable.exp)); myCategory.add(new Category("Vikas Datir",
	 * "It is an very difficult category to explain", R.drawable.exp));
	 * myCategory.add(new Category("Vikas Datir",
	 * "It is an very difficult category to explain", R.drawable.exp)); }
	 */
	
	//Adding Pop up menu to The Activity;
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	//Adding the experiments in the Exp List View.
	private void populateListView() {
		ArrayAdapter<Category> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.categoryListView);
		list.setAdapter(adapter);
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long ids) {
				catClickedId=categoryList.get(position).getCatId();
				
				Intent i = new Intent(MainActivity.this, ExperimentView.class);
				startActivity(i);
				finish();
			}
		});
	}

	private class MyListAdapter extends ArrayAdapter<Category> {

		public MyListAdapter() {
			super(MainActivity.this, R.layout.view_category, categoryList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.view_category,
						parent, false);
			}

			Category currentCategory = categoryList.get(position);

			ImageView imageView = (ImageView) itemView
					.findViewById(R.id.catImage);

			imageView.setImageResource(currentCategory.getCatImage());

			TextView catName = (TextView) itemView.findViewById(R.id.catName);
			catName.setText(currentCategory.getCatName());

			TextView catDesc = (TextView) itemView.findViewById(R.id.catDesc);
			catDesc.setText(currentCategory.getCatDesc());

			return itemView;
		}

	}
	
	// Action on click of pop up menu item.
	public boolean onContextItemSelected(MenuItem item) {
	    AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    switch (item.getItemId()) {
	    	case R.id.add:
	    		Intent i = new Intent(MainActivity.this, AddCategory.class);
				startActivity(i);
            return true;
	        case R.id.delete:
	        	catdb.open();
	        	catdb.deleteCategory(catDeleteId);
	        	catdb.close();
	        	Toast.makeText(MainActivity.this,"Record Has Been Deleted",Toast.LENGTH_LONG).show();
	            return true;
	        default:
			return super.onContextItemSelected(item);
	    }
	}
}
		

		
