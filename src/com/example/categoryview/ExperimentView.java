package com.example.categoryview;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ExperimentView extends Activity {

	// Creating Objects and Variable
	List<Experiment> expList = new ArrayList<Experiment>();
	ExperimentDbAdapter expdb = new ExperimentDbAdapter(this);
	static String expClickedName;
	static int expClickedId;
	int deleteId;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.activity_experiment_view);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.header);

		// Text View on Title Bar
		TextView txt = (TextView) findViewById(R.id.textScreenName);
		txt.setText("Experiments");
		
		
		// Creating Exp List View object
		View v = findViewById(R.id.expView);
		registerForContextMenu(v);

		// Opening Database Connection & reading Data & Closing Connection.
		expdb.open();
		expList = expdb.readExperiment(MainActivity.catClickedId);
		expdb.close();
		
		// To get id on long click listener..
		ListView list = (ListView) findViewById(R.id.expView);
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				deleteId= expList.get(arg2).getExpId();
				return false;
			}
		});

		// Adding all Experiments in ListView
		populateListView();

		// Code for Setting Button
		Button bOpenSetting = (Button) findViewById(R.id.settingButton);
		bOpenSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ExperimentView.this, AddExperiment.class);
				startActivity(i);
				finish();
			}
		});

		// Code for Home Button
		Button homeButton = (Button) findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(ExperimentView.this, MainActivity.class);
				startActivity(i);
				finish();

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
	}
	
	
	//Adding Pop up menu to The Activity;
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	//Adding the experiments in the Exp List View.
	private void populateListView() {
		ArrayAdapter<Experiment> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.expView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				
				expClickedId = expList.get(position).getExpId();
				expClickedName = expList.get(position).getExpName();
	
				ExperimentStepsView.a = expList.get(position).getNoOfSteps();
				Intent i = new Intent(ExperimentView.this,
						ExperimentStepsView.class);
		
				startActivity(i);
		
				finish();
			}
		});
	}
	

	private class MyListAdapter extends ArrayAdapter<Experiment> {

		public MyListAdapter() {
			super(ExperimentView.this, R.layout.view_experiment, expList);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			View itemView = convertView;
			if (itemView == null) {
				itemView = getLayoutInflater().inflate(
						R.layout.view_experiment, parent, false);
			}

			Experiment currentExperiment = expList.get(position);

			TextView expName = (TextView) itemView.findViewById(R.id.expName);
			expName.setText(currentExperiment.getExpName());

			TextView expDesc = (TextView) itemView.findViewById(R.id.expDesc);
			expDesc.setText(currentExperiment.getExpDesc());

			return itemView;
		}

	}
	
	// Action on click of pop up menu item.
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.add:
			Intent i = new Intent(ExperimentView.this, AddExperiment.class);
			startActivity(i);
			return true;

		case R.id.delete:
			ExperimentDbAdapter expDB = new ExperimentDbAdapter(this);
			StepDbAdapter stepdb = new StepDbAdapter(this);
			expDB.open();
			expDB.deleteExperiment(deleteId);
			expDB.close();
			
			stepdb.open();
			stepdb.deleteStep(deleteId);
			stepdb.close();
			
			Toast.makeText(ExperimentView.this, "Record Has Been Deleted",
					Toast.LENGTH_LONG).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}
	}
}